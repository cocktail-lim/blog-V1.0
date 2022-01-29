package com.finn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finn.dto.UserListPageDTO;
import com.finn.enums.ResultEnums;
import com.finn.service.UserService;
import com.finn.util.ResultUtils;
import com.finn.vo.UserQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping(value = "/api/admin/userList")
@Api(value = "UserController")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "根据用户角色和昵称分页查询用户列表")
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public ResultUtils getUserList(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                                   @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                                   @RequestParam(value = "roleName",required = false)String roleName,
                                   @RequestParam(value = "nickname",required = false) String nickname) {
        Page<UserListPageDTO> page = new Page<>(current,size);
        IPage<UserListPageDTO> userListPage = userService.getUserListPage(page, roleName, nickname);
        long total = userListPage.getTotal();
        List<UserListPageDTO> data = userListPage.getRecords();
        if(total > 0){
            return  ResultUtils.success().data("data", data).data("total", total);
        } else {
            return  ResultUtils.error().codeAndMessage(ResultEnums.NO_DATA_FOUND);
        }
    }

    @GetMapping(value = "/getUserListTest"  )
    public ResultUtils getUserListTest(@RequestParam(value = "nickname") String nickname) {
        return ResultUtils.success().data("data",userService.getUserListTest(nickname));
    }

    @GetMapping(value = "/getUserByCondition")
    public ResultUtils getUserByCondition(UserQueryVO userQueryVO) {
        return ResultUtils.success().data("data",userService.getUserByCondition(userQueryVO));
    }
}
