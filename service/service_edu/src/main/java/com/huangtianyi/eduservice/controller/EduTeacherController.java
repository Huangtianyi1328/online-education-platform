package com.huangtianyi.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huangtianyi.commonutils.Result;
import com.huangtianyi.eduservice.entity.EduTeacher;
import com.huangtianyi.eduservice.entity.vo.TeacherQuery;
import com.huangtianyi.eduservice.service.EduTeacherService;
import com.huangtianyi.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-21
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin  //解决跨域
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);

        return Result.ok().data("items",list);
    }

    //2.逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    //3.分页查询讲师方法
    @GetMapping("pageTeacher/{current}/{size}")
    public Result pageListTeacher(
            @ApiParam(name = "current", value = "当前页码", required = false) @PathVariable("current") long current,
            @ApiParam(name = "size", value = "页数据大小", required = false) @PathVariable("size") long size){
        //1.创建分页对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,size);
        //2.调用方法实现分页
        //调用方法时，底层封装，把分页所有数据封装到eduTeacherPage对象中
        eduTeacherService.page(eduTeacherPage,null);
        //3.总记录数
        long total = eduTeacherPage.getTotal();
        //4.当前页数据
        List<EduTeacher> records = eduTeacherPage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return Result.ok().data(map);
    }

    //4.条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public Result pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = false) @PathVariable("current") long current,
            @ApiParam(name = "size", value = "页数据大小", required = false) @PathVariable("size") long size,
            @ApiParam(name = "teacherQuery", value = "查询讲师条件", required = false)@RequestBody(required = false) TeacherQuery teacherQuery){
        //1.创建分页对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,size);
        //构造条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        wrapper.orderByDesc("gmt_create");
        //2.调用方法实现分页
        //调用方法时，底层封装，把分页所有数据封装到eduTeacherPage对象中
        eduTeacherService.page(eduTeacherPage,wrapper);
        //3.总记录数
        long total = eduTeacherPage.getTotal();
        //4.当前页数据
        List<EduTeacher> records = eduTeacherPage.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return Result.ok().data(map);
    }
    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

