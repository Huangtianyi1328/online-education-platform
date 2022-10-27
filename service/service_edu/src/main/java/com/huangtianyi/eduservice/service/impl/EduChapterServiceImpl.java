package com.huangtianyi.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangtianyi.eduservice.entity.EduChapter;
import com.huangtianyi.eduservice.entity.EduVideo;
import com.huangtianyi.eduservice.entity.chapter.ChapterVo;
import com.huangtianyi.eduservice.entity.chapter.VideoVo;
import com.huangtianyi.eduservice.mapper.EduChapterMapper;
import com.huangtianyi.eduservice.mapper.EduVideoMapper;
import com.huangtianyi.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangtianyi.eduservice.service.EduVideoService;
import com.huangtianyi.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id获取所有章节
        QueryWrapper<EduChapter> queryChapterWrapper = new QueryWrapper<>();
        queryChapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(queryChapterWrapper);

        //2.根据课程id获取所有小节
        QueryWrapper<EduVideo> queryVideoWrapper = new QueryWrapper<>();
        queryVideoWrapper.eq("course_id",courseId);
        List<EduVideo> videoList = videoService.list(queryVideoWrapper);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        //3.遍历查询章节list集合进行封装
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);

            chapterVoList.add(chapterVo);

            String chapterId = chapter.getId();

            List<VideoVo> VideoVoList = new ArrayList<>();
            //封装小节
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo video = videoList.get(j);

                if(chapterId.equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    VideoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(VideoVoList);
        }
        return chapterVoList;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        //判断
        if(count >0) {//查询出小节，不进行删除
            throw new GuliException(20001,"不能删除");
        } else { //不能查询数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            //成功  1>0   0>0
            return result>0;
        }
    }

    //2 根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper  = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
