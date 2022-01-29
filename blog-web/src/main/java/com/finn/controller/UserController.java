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
    public Result getUserList(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                              @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                              @RequestParam(value = "roleName",required = false)String roleName,
                              @RequestParam(value = "nickname",required = false) String nickname) {
        Page<UserListPageDTO> page = new Page<>(current,size);
        IPage<UserListPageDTO> userListPage = userService.getUserListPage(page, roleName, nickname);
        long total = userListPage.getTotal();
        List<UserListPageDTO> data = userListPage.getRecords();
        if(total > 0){
            return  Result.success().codeAndMessage(ResultEnums.SUCCESS).data("data", data).data("total", total);
        } else {
            return  Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
        }
    }

    @GetMapping(value = "/admin/userList/getUserByCondition")
    public Result getUserByCondition(UserQueryVO userQueryVO) { //不带传入参数说明，springboot会自动添加参数信息
        List<UserListPageDTO> users = userService.getUserByCondition(userQueryVO);
        long count = userService.count();
        if(!users.isEmpty()) {
            return Result.success().codeAndMessage(ResultEnums.SUCCESS).data("userList",users).data("total", count);
        } else
            return Result.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
    }

    @GetMapping(value = "/admin/userList/getUserListTest")
    public Result getUserListTest(@RequestParam(value = "nickname") String nickname) {
        return Result.success().data("data",userService.getUserListTest(nickname));
    }
}
