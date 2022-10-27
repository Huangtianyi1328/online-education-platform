package com.huangtianyi.eduservice.controller;


import com.huangtianyi.commonutils.Result;
import com.huangtianyi.eduservice.entity.subject.OneSubject;
import com.huangtianyi.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(tags = "课程分类管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return Result.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Result.ok().data("list",list);
    }
}

