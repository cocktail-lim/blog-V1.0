//package com.finn.security;
//
//import com.finn.entity.User;
//import com.finn.service.UserService;
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.stream.DoubleStream;
//
///*
// * @description: Token
// * @author: Finn
// * @create: 2022-01-22-17-31
// */
//@Component
//public class TokenUtils implements Serializable {
//    private static final long serialVersionUID = -3L;
//    /**
//     * Token 有效时长
//     */
//    private static final Long EXPIRATION = 604800L;
//
//    @Autowired
//    UserService userService;
//
//    /*
//    * @Description:  生成 Token 字符串。必须 setAudience 接收者， setExpiration 过期时间， role 用户角色
//    * @Param: [user] 用户信息
//    * @return:  生成的Token字符串 or null
//    * @Author: Finn
//    * @Date: 2022/1/22
//    */
//    public String createToken(User user) {
//
//        try {
//            // Token 的过期时间
//            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
//            // 生成 Token
//            String token = Jwts.builder()
//                    // 设置 Token 签发者 可选
//                    .setIssuer("SpringBoot")
//                    // 根据用户名设置 Token 的接受者
//                    .setAudience(user.getUsername())
//                    // 设置过期时间
//                    .setExpiration(expirationDate)
//                    // 设置 Token 生成时间 可选
//                    .setIssuedAt(new Date())
//                    // 通过 claim 方法设置一个 key = role，value = userRole 的值
//                    .claim("role", userService.listUserRolesByUsername(user.getUsername()))
//                    // 设置加密密钥和加密算法，注意要用私钥加密且保证私钥不泄露
//                    .signWith(RsaUtils.getPrivateKey(), SignatureAlgorithm.)
//                    .compact();
//            return String.format("Bearer %s", token);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /** 验证 Token ，并获取到用户名和用户权限信息
//     * @param token Token 字符串
//     * @return sysUser 用户信息
//     */
//    public User validationToken(String token) {
//        try {
//            // 解密 Token，获取 Claims 主体
//            Claims claims = Jwts.parserBuilder()
//                    // 设置公钥解密，以为私钥是保密的，因此 Token 只能是自己生成的，如此来验证 Token
//                    .setSigningKey(RsaUtils.getPublicKey())
//                    .build().parseClaimsJws(token).getBody();
//            assert claims != null;
//            // 验证 Token 有没有过期 过期时间
//            Date expiration = claims.getExpiration();
//            // 判断是否过期 过期时间要在当前日期之后
//            if (!expiration.after(new Date())) {
//                return null;
//            }
//            SysUser sysUser = new SysUser();
//            sysUser.setUsername(claims.getAudience());
//            sysUser.setUserRole(claims.get("role").toString());
//            return sysUser;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}