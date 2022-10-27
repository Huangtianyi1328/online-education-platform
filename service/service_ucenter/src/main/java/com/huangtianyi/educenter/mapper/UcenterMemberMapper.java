package com.huangtianyi.educenter.mapper;

import com.huangtianyi.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-10-07
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
