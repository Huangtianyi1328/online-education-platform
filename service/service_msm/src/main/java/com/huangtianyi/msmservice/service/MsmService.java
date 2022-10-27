package com.huangtianyi.msmservice.service;

import java.util.Map;

public interface MsmService {
    //发送短信的方法
    boolean send(String code, String phone);
}
