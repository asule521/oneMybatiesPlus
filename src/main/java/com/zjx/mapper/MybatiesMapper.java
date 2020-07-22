package com.zjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.pojo.AgeUser;
import com.zjx.pojo.TestUser;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MybatiesMapper extends BaseMapper<TestUser> {

    List<AgeUser> selectitems(Page page);

    List<TestUser> sql1(Page<TestUser> tPage,int id);
}
