package com.huangtianyi.msmservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.huangtianyi.commonutils.Result;
import com.huangtianyi.msmservice.service.MsmService;
import com.huangtianyi.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //2 如果redis获取 不到，进行发送
        //生成随机值，进行发送
        code = RandomUtil.getFourBitRandom();
        boolean isSend = msmService.send(code,phone);
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            return Result.ok();
        } else {
            return Result.error().message("短信发送失败");
        }
    }
}
