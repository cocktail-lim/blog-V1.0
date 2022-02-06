package com.finn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * @description: 用于分页的页表信息
 * @author: Finn
 * @create: 2022/02/05 19:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPage<T> {

    /*分页列表*/
    private List<T> records;

    /*总数*/
    private long total;

}
