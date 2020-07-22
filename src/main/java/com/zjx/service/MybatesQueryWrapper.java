package com.zjx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjx.pojo.TestUser;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangjinxing
 * @date 2020/7/21 14:35
 */

public interface MybatesQueryWrapper {
    IPage QueryWrapper(Map map);
}
