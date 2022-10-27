package com.huangtianyi.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.huangtianyi.commonutils.Result;
import com.huangtianyi.eduservice.entity.EduCourse;
import com.huangtianyi.eduservice.entity.vo.CourseInfoVo;
import com.huangtianyi.eduservice.entity.vo.CoursePublishVo;
import com.huangtianyi.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(tags = "课程管理")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return Result.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return Result.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Result.ok();
    }
}

