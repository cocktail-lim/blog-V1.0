package com.finn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/*
 * @description: token entity
 * @author: Finn
 * @create: 2022-01-24-17-19
 */
@Setter
@Getter
@ToString
public class Token {

    private Integer id;
//    private String openId;
    private Set<String> role;
    private Date lastLogin;

}
