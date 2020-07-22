package com.zjx.mapper2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjx.pojo.TestUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatiesMapper2 extends BaseMapper<TestUser> {
}
