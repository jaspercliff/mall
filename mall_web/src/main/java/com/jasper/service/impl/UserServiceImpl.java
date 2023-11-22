package com.jasper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.common_utils.R;
import com.jasper.config.CustomException;
import com.jasper.dto.UserDto;
import com.jasper.mapper.UserMapper;
import com.jasper.pojo.User;
import com.jasper.pojo.UserResource;
import com.jasper.service.UserResourceService;
import com.jasper.service.UserService;
import com.jasper.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jasper.config.RedisConstants.CACHE_RESOURCE_LIST_KEY;
import static com.jasper.config.RedisConstants.CACHE_RESOURCE_LIST_KEY_TTL;

/**
* @author 21903
* @description 针对表【user(会员表)】的数据库操作Service实现
* @createDate 2023-06-18 23:38:01
*/
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserResourceService userResourceService;
    private final JwtUtil jwtUtil;
    @Override
    public R<?> register(UserDto userDto,String emailcode) {
        String emailCodeCache = stringRedisTemplate.opsForValue().get("emailCode");
        if(StrUtil.isEmpty(emailCodeCache)){
            return R.fail("验证码已经过期，请重新发送");
        }
        if(!emailcode.equals(emailCodeCache)){
            return R.fail("验证码错误，请重新输入");
        }
        String pw =  passwordEncoder.encode(userDto.getPassword());
        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        user.setPassword(pw);
        boolean save = this.save(user);
        return R.success("注册成功",save);
    }

    @Override
    public R<?> getUserByEmail(String email){
        User one = this.getOne(new LambdaUpdateWrapper<User>().eq(User::getEmail, email));
        if(one == null){
            return R.fail("该用户不存在，请重新输入！！");
            }
        return R.success(one.getUsername());
    }

    @Override
    public R<?> login(UserDto userDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        if(!passwordEncoder.matches(userDto.getPassword(),userDetails.getPassword())){
            throw new CustomException(2003,"password is wrong!!");
        }
        if(!userDetails.isEnabled()){
            throw new CustomException(2004,"账号被禁用");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //将给定的身份验证对象设置为当前线程的身份验证对象
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        String token = jwtUtil.generateToken(userDetails);
        log.error("token="+token +"end");
        return R.success(token);
    }

    @Override
    public R<?> getUserByUsername(String username) {
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User one = this.getOne(eq);
        if (one!= null){
            return R.fail("用户名已存在");
         }
        return R.success();
    }

    public List<UserResource> getResourceList(Long userId){
//        get from redis
        List<String> resouceList = stringRedisTemplate.opsForList().range(CACHE_RESOURCE_LIST_KEY, 0, -1);
        if ((resouceList != null ? resouceList.size() : 0) !=0) {
            ArrayList<UserResource> userResources = new ArrayList<>();
            resouceList.forEach(
                    resource -> {
                        UserResource bean = JSONUtil.toBean(resource, UserResource.class);
                        userResources.add(bean);
                    }
            );
            return userResources;
        }
//        null get from redis get from database
        List<UserResource> userResources = userResourceService.list(null);
        ArrayList<String> resourceList = new ArrayList<>();
        userResources.forEach(
             userResource ->
             {
                 String jsonStr = JSONUtil.toJsonStr(userResource);
                 resourceList.add(jsonStr);
             }
        );
//store in redis
        stringRedisTemplate.opsForList().rightPushAll(CACHE_RESOURCE_LIST_KEY,resourceList);
        stringRedisTemplate.expire(CACHE_RESOURCE_LIST_KEY,CACHE_RESOURCE_LIST_KEY_TTL, TimeUnit.MINUTES);
        return userResources;
    }

}




