package com.ok.okhelper.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
*Author:zhangxin_an
*Description:
*Data:Created in 22:21 2018/4/10
*/
public class JWTUtil {
	
	// 过期时间5分钟
	private static final long EXPIRE_TIME = 5*60*1000;
	public final  static String AUTH_TOKEN = "token";
	public final  static String SECRET = "token";
	
	/**
	 * 校验token是否正确
	 * @param token 密钥
	 * @param secret 用户的密码
	 * @return 是否正确
	 */
	public static boolean verify(String token, String username, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("username", username)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
	
	/**
	 * 获得token中的信息无需secret解密也能获得
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
	 * 生成签名,5min后过期
	 * @param username 用户名
	 * @param secret 用户的密码
	 * @return 加密的token
	 */
	public static String sign(String username, String secret,String []permissions) {
		try {
			Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			// 附带username信息
			return JWT.create()
					.withClaim("username", username)
					.withArrayClaim("permissions",permissions)
					.withExpiresAt(date)
					.sign(algorithm);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		List<String> code = new ArrayList<>();
		for(int i = 0;i<1000;i++){
			code.add("user:afa"+i);
		}
		String []codeStrings = (String[]) code.toArray(new String[code.size()]);
		
		for(String s : codeStrings){
			System.out.print(s+"\t");
		}
		String token = sign("zxa","token",codeStrings);
		
		String []c = getPermissions(token);
		System.out.println("\n解析");
		
		for(String s : c){
			System.out.print(s+"\t");
		}
		
	}

	public static String[] getPermissions(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("permissions").asArray(String.class);
		} catch (JWTDecodeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}