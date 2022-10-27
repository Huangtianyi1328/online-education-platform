package com.huangtianyi.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.huangtianyi.commonutils.Result;
import com.huangtianyi.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //发送短信的方法
    @Override
    public boolean send(String code, String phone) {
        if(StringUtils.isEmpty(phone)) return false;
        try {
            //发送成功，把发送成功验证码放到redis里面
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
