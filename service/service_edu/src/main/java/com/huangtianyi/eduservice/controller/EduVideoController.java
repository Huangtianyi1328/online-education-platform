package com.huangtianyi.eduservice.controller;


import com.huangtianyi.commonutils.Result;
import com.huangtianyi.eduservice.client.VodClient;
import com.huangtianyi.eduservice.entity.EduChapter;
import com.huangtianyi.eduservice.entity.EduVideo;
import com.huangtianyi.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Result.ok();
    }
    //删除小节
    //删除小节同时把里面视频删掉
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo video = eduVideoService.getById(id);
        String videoSourceId = video.getVideoSourceId();

        //判断小节里面是否有视频id
        if(StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用实现视频删除
            vodClient.removeAlyVideo(videoSourceId);
        }

        eduVideoService.removeById(id);
        return Result.ok();
    }
    //修改小节 TODO
    @PostMapping("updateVideo")
    public Result updateChapter(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }
    //根据章节id查询
    @GetMapping("getVideoInfo/{id}")
    public Result getChapterInfo(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return Result.ok().data("video",eduVideo);
    }

}

