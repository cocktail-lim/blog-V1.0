package com.finn.controller;


import com.finn.dto.RoleSelectListDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.RoleService;
import com.finn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    /* 
    * @Description: 用户列表-角色下拉菜单
    * @Param: [] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/1/29 
    */
    @GetMapping("/api/admin/role/getRoleSelectList")
    public Result getRoleList() {
        List<RoleSelectListDTO> userRoleSelectList = roleService.getUserRoleSelectList();
        if(!userRoleSelectList.isEmpty())
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("roleList", userRoleSelectList);
        else
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
    }
}
