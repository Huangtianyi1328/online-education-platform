package com.huangtianyi.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.huangtianyi.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //解决跨域
@Api(tags = "登录")
public class EduLoginController {
    //login
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token","admin");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
