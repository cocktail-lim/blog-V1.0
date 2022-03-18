package com.finn.vo;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/*
 * @description: 删除文章
 * @author: Finn
 * @create: 2022/02/07 11:56
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteVO {

    /*要删除的id列表*/
    @NotNull
    private List<Integer> idList;

    /*是否将逻辑删除置为1*/
    private Integer isDelete;
}
