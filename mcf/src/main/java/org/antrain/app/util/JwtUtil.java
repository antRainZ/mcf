package org.antrain.app.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.antrain.common.exception.CustomException;
import org.antrain.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {
    /**
     * 过期时间为1天
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;


    /**
     * token私钥
     */
    public static final String KEY = "antrain_wyz_lyh";

    public static String create(User user) {

        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("id", user.getId())
                .withClaim("username",user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(KEY));
    }

    public static void isExpire(String token){
        if(System.currentTimeMillis() - JWT.decode(token).getExpiresAt().getTime() > 0 ){
            throw new CustomException("登录已过期，请重新登录");
        }
    }

    public static Long getId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return JWT.decode(token).getClaim("id").asLong();
    }

    public static String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return JWT.decode(token).getClaim("username").asString();
    }
}
