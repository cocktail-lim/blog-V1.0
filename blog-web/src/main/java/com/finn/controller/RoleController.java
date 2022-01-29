package com.finn.controller;


import com.finn.enums.ResultEnums;
import com.finn.service.RoleService;
import com.finn.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/api/admin/userList")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/getRoleList")
    public ResultUtils getRoleList() {
        return ResultUtils.success().codeAndMessage(ResultEnums.SUCCESS).data("roleList", roleService.getUserRoleList());
    }
}
