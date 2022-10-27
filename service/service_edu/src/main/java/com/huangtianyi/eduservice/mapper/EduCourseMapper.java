package com.huangtianyi.eduservice.mapper;

import com.huangtianyi.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangtianyi.eduservice.entity.frontvo.CourseWebVo;
import com.huangtianyi.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPulishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
