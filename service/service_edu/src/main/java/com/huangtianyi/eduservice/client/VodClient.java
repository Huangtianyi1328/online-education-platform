package com.huangtianyi.eduservice.client;

import com.huangtianyi.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    //根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable("id") String id);

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
