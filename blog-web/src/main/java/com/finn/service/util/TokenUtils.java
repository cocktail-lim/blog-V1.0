package com.finn.service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.finn.entity.Token;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

/*
 * @description:
 * 1.用户调用登录接口后、验证用户名和密码。验证成功后、颁发给其token
 * 2.前台获得 token 后，将其存放到本地、每次的请求都将这个token 携带到请求头里面。
 * 3.后台收到请求后、验证请求头里面的 Authorization 是否正确、从而判断是否可以调用这个接口。
 * 4.通过解析 token 将账号信息存入 userDetail 让其顺利调用接口信息、并可以在接口中获得当前登录人的账号信息。
 * @author: Finn
 * @create: 2022/01/29 19:46
 */
//@Component
public class TokenUtils {

    //过期时间设置(30分钟)
//    private static final long EXPIRE_TIME = 30*60*1000;
    private static final long EXPIRE_TIME = 10;

    //私钥设置(随便乱写的)
    private static final String TOKEN_SECRET = "8xOoVRxNysDIxK1l2R78nw";

    /*
    * @Description: 创建Token对象
    * @Param: [role]
    * @return: java.lang.String
    * @Author: Finn
    * @Date: 2022/02/02 17:09
    */
    public String creatToken(String username, Set<String> role){
        // 加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 过期时间和加密算法设置
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 创建token
        Token token = new Token();
        token.setUsername(username);
        token.setRole(role);
        token.setLastLogin(date);

        //头部信息
        Map<String,Object> header = new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        List<String> tokenRoles = new ArrayList<>(token.getRole()); // Set 转 List
        return JWT.create()
                .withHeader(header)
                .withClaim("username", username)
                .withClaim("role", tokenRoles)
                .withClaim("lastLogin",token.getLastLogin())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public Token parseToken(String token){
        DecodedJWT jwt = JWT.decode(token);

        Token tk = new Token();
        Set<String> roles = new HashSet<>(Arrays.asList(jwt.getClaim("role").toString()));
        tk.setUsername(jwt.getClaim("username").asString());
        tk.setRole(roles);
        tk.setLastLogin(jwt.getClaim("lastLogin").asDate());

        return tk;
    }

    public Long getTokenExpireTime(){
        return EXPIRE_TIME;
    }
}
