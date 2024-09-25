package com.quanxiaoha.weblog.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenHelper implements InitializingBean {

    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    private String issuer;
    /**
     * 秘钥
     */
    private Key key;

    /**
     * JWT 解析
     */
    private JwtParser jwtParser;

    /**
     * 解码配置文件中配置的 Base 64 编码 key 为秘钥
     * @param base64Key
     */
    @Value("${jwt.secret}")
    public void setBase64Key(String base64Key) {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
    }


    /**
     * 初始化 JwtParser
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 考虑到不同服务器之间可能存在时钟偏移，setAllowedClockSkewSeconds 用于设置能够容忍的最大的时钟误差
        jwtParser = Jwts.parserBuilder().requireIssuer(issuer)
                .setSigningKey(key).setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 生成 Token
     * @param username
     * @return
     */
    /**
     * Token 失效时间（分钟）
     */
    @Value("${jwt.tokenExpireTime}")
    private Long tokenExpireTime;
//    Instant 是 Java 8 中引入的 java.time 包中的一个类，用于表示一个特定的时刻，精确到纳秒，基于 UTC（协调世界时）时区。Instant 类是不可变的，并且自 Unix 纪元（1970-01-01T00:00:00Z）开始的秒数和纳秒数来表示时间。
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // Token 一个小时后失效
        LocalDateTime expireTime = now.plusMinutes(tokenExpireTime);
        //包含用户唯一标识（用户名），创建一个 JWT （JSON WEB TOKEN）构建器
        return Jwts.builder().setSubject(username)
//  签发者
                .setIssuer(issuer)
//  设置 JWT 的发行时间（iat）声明，使用系统默认时区将 LocalDateTime 转换为 Instant，然后转换为 Date。
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
//  设置 JWT 的过期时间（exp）声明，同样使用系统默认时区进行转换。
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
//  使用签名密钥对 JWT 进行签名。这里的 key 应该是一个在方法外部定义的 SecretKey 对象，用于生成 Token 的签名。
                .signWith(key)
//  生成并返回一个紧凑格式（URL 安全的 base64 编码字符串）的 JWT
                .compact();
    }

    /**
     * 解析 Token
     * @param token
     * @return
     */
    public Jws<Claims> parseToken(String token) {
//  如果解析和验证成功，可以从解析后的 Claims 对象中获取 JWT 中包含的信息，如用户 ID、角色等。如果解析过程中出现异常，则表示 JWT 无效或已过期。
        try {
            return jwtParser.parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new BadCredentialsException("Token 不可用", e);
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("Token 失效", e);
        }
    }

    /**
     * 生成一个 Base64 的安全秘钥
     * @return
     */
    private static String generateBase64Key() {
        // 生成安全秘钥
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 将密钥进行 Base64 编码

        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * 校验 Token 是否可用
     * @param token
     * @return
     */
    public void validateToken(String token) {
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析 Token 获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String key = generateBase64Key();
        System.out.println("key: " + key);
    }
}
