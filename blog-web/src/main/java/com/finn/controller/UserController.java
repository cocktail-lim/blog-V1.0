package com.finn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.UserListPageDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.UserService;
import com.finn.util.Result;
import com.finn.vo.UserQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/api")
@Api(value = "UserController")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "根据用户角色和昵称分页查询用户列表")
    @GetMapping(value = "/admin/userList/getUserList")
    public Result getUserByCondition(UserQueryVO userQueryVO) { //不带传入参数说明，springboot会自动添加参数信息
        Page<UserListPageDTO> page = new Page<>(userQueryVO.getCurrent(), userQueryVO.getSize());
        IPage<UserListPageDTO> userList = userService.getUserList(page, userQueryVO);
        long total = userList.getTotal();
        List<UserListPageDTO> data = userList.getRecords();
        if(!data.isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("userList",data).data("total", total);
        } else
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
    }

    @GetMapping(value = "/admin/userList/getUserListTest")
    public Result getUserListTest(@RequestParam(value = "nickname") String nickname) {
        return Result.success().data("data", userService.getUserListTest(nickname));
    }
}
