package com.finn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description: 查询条件
 * @author: Finn
 * @create: 2022/03/17 09:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询条件")
public class ConditionVO {

    @ApiModelProperty(name = "keywords", value = "搜索内容", dataType = "String")
    String Keywords;
}
