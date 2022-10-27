package com.huangtianyi.eduservice.service.impl;

import com.huangtianyi.eduservice.entity.EduCourse;
import com.huangtianyi.eduservice.entity.EduCourseDescription;
import com.huangtianyi.eduservice.entity.vo.CourseInfoVo;
import com.huangtianyi.eduservice.mapper.EduCourseDescriptionMapper;
import com.huangtianyi.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangtianyi.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {


}
