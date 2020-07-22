package com.zjx.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.Utils.PageUtils;
import com.zjx.Utils.Query;
import com.zjx.mapper2.MybatiesMapper2;
import com.zjx.pojo.TestUser;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service("MybatiesService2")
public class MybatiesServiceImpl2 extends ServiceImpl<MybatiesMapper2, TestUser> implements MybatiesService2 {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TestUser> page = this.page(
                new Query<TestUser>().getPage(params),
                new QueryWrapper<TestUser>()
        );

        return new PageUtils(page);
    }
}
