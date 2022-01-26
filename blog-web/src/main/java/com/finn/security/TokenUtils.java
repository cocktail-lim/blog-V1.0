package com.finn.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.finn.entity.Token;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class TokenUtils {

    //过期时间设置(30分钟)
    private static final long EXPIRE_TIME = 30*60*1000;

    //私钥设置(随便乱写的)
    private static final String TOKEN_SECRET = "5xcJVrXNyQDIxK1l2RS9nw";

    public String getToken(Token token){

        //过期时间和加密算法设置
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm =Algorithm.HMAC256(TOKEN_SECRET);

        //头部信息
        Map<String,Object> header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        List<String> tokenRoles = new ArrayList<>(token.getRole()); // Set 转 List
        return JWT.create()
                .withHeader(header)
                .withClaim("role", tokenRoles)
                .withClaim("lastLogin",token.getLastLogin())
                .withExpiresAt(date)
                .sign(algorithm);

    }

    public Token getTokenData(String token){
        DecodedJWT jwt = JWT.decode(token);
        Token tk = new Token();

        Set<String> set = new HashSet<>(Arrays.asList(jwt.getClaim("role").toString()));
        tk.setRole(set);
        tk.setLastLogin(jwt.getClaim("lastLogin").asDate());

        return tk;
    }

    public String creatToken(Set<String> role){
        //这里是传入的是token对象，决定token的内容
        Token tk=new Token();
        //获取时间用
        Date date=new Date();

        tk.setRole(role);
        tk.setLastLogin(date);
        //交给上面的实现类得到token
        return getToken(tk);
    }

    public String getTokenDataOpenId(String token){
        return JWT.decode(token).getClaim("openId").asString();
    }
}
