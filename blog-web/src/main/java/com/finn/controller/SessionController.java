package com.finn.controller;

import com.finn.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description: session控制
 * @author: Finn
 * @create: 2022/02/03 15:07
 */
@RestController
public class SessionController {

    @GetMapping(value = "/session/invalid")
    public Result sessionInvalid(){
        return Result.error().codeAndMessage("669", "Session已过期，请重新登陆");
    }
}
