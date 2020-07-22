package com.zjx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.Utils.PageUtils;
import com.zjx.pojo.AgeUser;
import com.zjx.pojo.TestUser;

import java.util.Map;

public interface MybatiesService extends IService<TestUser> {
    PageUtils queryPage(Map<String, Object> params);


    IPage<AgeUser> selectTwo(Map<String, Object> map);

    PageUtils queryPage1(Map<String, Object> map);

    IPage<TestUser> sql1(Map<String, Object> map,int id);


}
