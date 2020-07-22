package com.zjx.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.Utils.PageUtils;
import com.zjx.mapper.MybatesQueryWrapperMapper;
import com.zjx.pojo.TestUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangjinxing
 * @date 2020/7/21 14:35
 */
@Service
public class MybatesQueryWrapperImpl implements MybatesQueryWrapper{

    @Autowired
    private MybatesQueryWrapperMapper mMybatesQueryWrapperMapper;
    @Override
    public IPage<TestUser> QueryWrapper(Map map) {
        Page<TestUser> objectPage = new Page<>();
        objectPage.setPages((Long) map.get("page"));
        objectPage.setSize((Long) map.get("limit"));
        QueryWrapper<TestUser> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username","张三");
        IPage<TestUser> testUserIPage = mMybatesQueryWrapperMapper.selectPage(objectPage, objectQueryWrapper);
        return  testUserIPage;

    }
}
