package com.finn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @description: 分页
 * @author: Finn
 * @create: 2022/02/05 19:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {

    /*分页列表*/
    private List<T> records;

    /*总数*/
    private long total;

}
