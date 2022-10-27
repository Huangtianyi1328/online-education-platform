package com.huangtianyi.educms.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.huangtianyi.commonutils.Result;
import com.huangtianyi.educms.entity.CrmBanner;
import com.huangtianyi.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前台bannber显示
 * </p>
 *
 * @author testjava
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Result.ok().data("list",list);
    }
}

