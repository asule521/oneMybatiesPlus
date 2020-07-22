package com.zjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.Utils.PageUtils;
import com.zjx.pojo.TestUser;

import java.util.Map;

public interface MybatiesService2 extends IService<TestUser> {
    PageUtils queryPage(Map<String, Object> params);
}
