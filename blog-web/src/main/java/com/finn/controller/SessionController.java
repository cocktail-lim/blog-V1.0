package com.finn.controller;

import com.finn.service.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @description: session控制
 * @author: Finn
 * @create: 2022/02/03 15:07
 */
@RestController
@RequestMapping("/api")
public class SessionController {

    @GetMapping(value = "/api/session/invalid")
    public Result sessionInvalid(){
        return Result.error().codeAndMessage("669", "Session已过期，请重新登陆");
    }
}
