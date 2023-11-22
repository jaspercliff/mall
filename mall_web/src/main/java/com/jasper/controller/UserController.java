package com.jasper.controller;

import cn.hutool.core.util.RandomUtil;
import com.jasper.common_utils.R;
import com.jasper.dto.UserDto;
import com.jasper.service.UserService;
import com.jasper.utils.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author com
 * 6/18/2023  11:30 PM
 */
@RestController
@RequestMapping("webService/user")
@AllArgsConstructor
@Lazy
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R<?> login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }

    @GetMapping("/getUserByUsername/{username}")
    public R<?> getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping("/getUserByEmail/{email}")
    public R<?> getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }
    @PostMapping("/register/{emailcode}")
    public R<?> register(@RequestBody UserDto user, @PathVariable("emailcode") String emailcode) {
        return userService.register(user,emailcode);
    }

    @RequestMapping("code/{email}")
    public R<?> emailCode(@PathVariable("email") String email){
        int emailCode = RandomUtil.randomInt(100000,999999);
        redisTemplate.opsForValue().set("emailCode", String.valueOf(emailCode),10, TimeUnit.MINUTES);
        mailService.sendMail(email,"验证您的mall账户,","请输入以下代码：\n" +
                emailCode + "\n" +
                "验证码有效时间为10分钟,请勿与任何人共享此代码，因为它会帮助他们访问您的mall账户。\n" +
                "谢谢！");
        return R.success();
    }
}
