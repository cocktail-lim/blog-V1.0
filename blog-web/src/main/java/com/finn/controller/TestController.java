package com.finn.controller;

import com.finn.service.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/*
 * @description: controller类
 * @author: Finn
 * @create: 2022-01-09-18-44
 */
@Api(tags = "测试模块")
@RestController
public class TestController {

//    @Autowired
//    private TestService testService;

//    @GetMapping("/test")
//    public Result test() {
//        String test = testService.test();
//        if(!StringUtils.isEmpty(test)) {
//            return Result.success().codeAndMessage(ResultInfo.SUCCESS).setData(new HashMap<String, Object>
//                    (){{put("data0", test);put("data1", test);}});
//        } else {
//            return Result.error().codeAndMessage(ResultInfo.NOT_FOUND);
//        }
////        throw new MyRuntimeException("121", "自定义异常");
//
//    }

//    @CrossOrigin(origins = {"10.12.37.207:3000"})
    @ApiOperation("测试接口")
    @GetMapping("/api/test")
    public Result test(){
//        User user = UserService.getUserById(1);
//        System.out.println(user.toString());
        return Result.success().codeAndMessage("200", "王宁傻逼");
    }

}
