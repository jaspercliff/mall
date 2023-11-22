package com.jasper.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author com
 * 6/20/2023  5:25 PM
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
//    用户名
    private static final String CLAIM_KEY_EMAIL = "email";
//    创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * generate token
     * @param claims payload
     * @return token
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  //json
                .setExpiration(generateExpirationDate())
                .signWith(key)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("jwt 格式验证失败：{}", token);
        }
        return claims;
    }


    /**
     * claims.getSubject()
     * @param token token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        String  username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_EMAIL);
        }catch (Exception e){
            e.printStackTrace();
            username = null;
        }
        return username;
    }

    private boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * claims.getExpiration()
     * @param token token
     * @return expiration time
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * validate token
     * @param token token
     * @param userDetails query user form database
     * @return isvalidate
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(CLAIM_KEY_EMAIL,userDetails.getUsername());
        map.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(map);
    }

    public String refreshToken(String oldToken) {
        if(StrUtil.isEmpty(oldToken)){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(StrUtil.isEmpty(token)){
            return null;
        }
        Claims claims = getClaimsFromToken(token);
        if(claims==null){
            return null;
        }
        if(isTokenExpired(token)){
            return null;
        }
        if(tokenRefreshJustBefore(token,30*60)){
            return token;
        }else {
            claims.put(CLAIM_KEY_CREATED,new Date());
            return generateToken(claims);
        }
    }

    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        return refreshDate.after(created)&&refreshDate.before(DateUtil.offsetSecond(created,time));
    }
}
