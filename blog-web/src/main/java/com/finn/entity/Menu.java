package com.finn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author finn
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String menuName;

    private String url;

    private String icon;

    private Integer parentId;

    private Boolean enable;


}
