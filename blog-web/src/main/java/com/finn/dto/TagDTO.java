package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/*
 * @description: TagDTO
 * @author: Finn
 * @create: 2022/02/04 15:27
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TagDTO {

    @TableId(type = IdType.AUTO)
    private Integer tagId;

    private String tagName;

}
