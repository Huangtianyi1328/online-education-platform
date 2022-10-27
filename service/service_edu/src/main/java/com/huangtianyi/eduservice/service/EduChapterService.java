package com.huangtianyi.eduservice.service;

import com.huangtianyi.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huangtianyi.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
