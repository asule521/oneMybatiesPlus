package com.zjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjx.pojo.TestUser;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangjinxing
 * @date 2020/7/21 14:37
 */
@Mapper
public interface MybatesQueryWrapperMapper extends BaseMapper<TestUser> {
}
