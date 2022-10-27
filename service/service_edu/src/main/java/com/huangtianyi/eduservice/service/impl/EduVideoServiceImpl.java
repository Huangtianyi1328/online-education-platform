package com.huangtianyi.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangtianyi.eduservice.client.VodClient;
import com.huangtianyi.eduservice.entity.EduVideo;
import com.huangtianyi.eduservice.mapper.EduVideoMapper;
import com.huangtianyi.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    //1. 根据课程id删除小节
    //TODO 删除小节，删除对应视频文件
    @Override
    public void removeVideoByCourseId(String courseId) {
        //1.根据课程id查询课程中所有的视频id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapper);

        // List<EduVideo>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }

        //根据多个视频id删除多个视频
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }

        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
