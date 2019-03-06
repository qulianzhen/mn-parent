package com.mn.mnutil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * 在这个工具类中可以实现对用户名和密码的加密处理，校验 token 是否正确，获取用户名等操作
 * Algorithm algorithm = Algorithm.HMAC256(password) 是对密码进行加密后再与用户名混淆在一起
 * 在签名时可以通过 .withExpiresAt(date) 指定 token 的过期时间
 */
public class JwtUtil {
    private static final long EXPIRE_TIME = 5 * 60 * 1000;//5分钟

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param username 登录名
     * @param password 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String password) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息,无需secret解密也能获得
     * 获取用户名随便获取，因为本来就知道，但是不知道密码，所以，只是拿到用户名，还是过不了verify那一关，
     * 也就是说取用户名可以不用校验！都知道了；
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            if(StringUtil.isBlank(token)){
                return null;
            }
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param password   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String password) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);

    }

    public static void main(String[] args) {
        String token1 = JwtUtil.sign("张三","123321");
        String token2 = JwtUtil.sign("lisisss","556633");
        System.out.println(token1);
        System.out.println("======================");
        System.out.println(JwtUtil.getUsername(token2));
    }
}
