package com.ok.okhelper.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ok.okhelper.util.PropertiesUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 22:21 2018/4/10
 */
public class JWTUtil {

    private static final long EXPIRE_TIME_PREFIX = Long.parseLong(Objects.requireNonNull(PropertiesUtil.getProperty("token.jwt.expireTime")));
    private static final long EXPIRE_TIME = EXPIRE_TIME_PREFIX * 1000;
    private final static String ISSUER = "OkHelper";
//    public final static String SECRET = "token";

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取用户ID
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取storeId
     *
     * @param token
     * @return
     */
    public static Long getStoreId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("storeId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(Long userId, String username, String secret, Long storeId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("username", username)
                    .withClaim("storeId", storeId)
                    .withIssuer(ISSUER)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static String getToken() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal().toString();
    }


    public static String getUsername() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    public static Long getUserId() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    public static Long getStoreId() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getClaim("storeId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    public static Date getExpiresAt() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}