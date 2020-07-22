package com.zjx.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjx.Utils.PageUtils;
import com.zjx.Utils.Query;
import com.zjx.mapper.MybatiesMapper;

import com.zjx.pojo.AgeUser;
import com.zjx.pojo.TestUser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service()
public class MybatiesServiceImpl extends ServiceImpl<MybatiesMapper, TestUser> implements MybatiesService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TestUser> page = this.page(
                new Query<TestUser>().getPage(params),
                new QueryWrapper<TestUser>()
        );
        return new PageUtils(page);
    }

    @Override
    public IPage<AgeUser> selectTwo(Map<String, Object> map) {
        Page<AgeUser> tPage = new Page<AgeUser>(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<AgeUser> ageUser =baseMapper.selectitems(tPage);
        Page<AgeUser> ageUserPage = tPage.setRecords(ageUser);
        return ageUserPage;
    }

    @Override
    public PageUtils queryPage1(Map<String, Object> map) {
        IPage<TestUser> page = this.page(
                new Query<TestUser>().getPage(map),
                new QueryWrapper<TestUser>()
        );
        return new PageUtils(page);
    }

    @Override
    public IPage<TestUser> sql1(Map<String, Object> map,int id) {
        Page<TestUser> tPage = new Page<TestUser>(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<TestUser> ageUser =baseMapper.sql1(tPage,id);
        Page<TestUser> ageUserPage = tPage.setRecords(ageUser);
        return ageUserPage;
    }


}
