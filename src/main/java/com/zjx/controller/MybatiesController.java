package com.zjx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.zjx.Utils.PageUtils;
import com.zjx.pojo.AgeUser;
import com.zjx.pojo.TestUser;
import com.zjx.service.MybatesQueryWrapper;
import com.zjx.service.MybatesQueryWrapperImpl;
import com.zjx.service.MybatiesService;
import com.zjx.service.MybatiesService2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("mybaties")
@Controller
@Slf4j(topic = "sys-user")
public class MybatiesController {
    @Autowired
    private MybatiesService mMybatiesService;
    @Autowired
    private   MybatiesService2 mMybatiesService2;
    @Autowired
    private MybatesQueryWrapper mMybatesQueryWrapper;

    @RequestMapping("save")
    public void save(){

        TestUser testUser = new TestUser();
        testUser.setId("0519");
        testUser.setUsername("asule");
        testUser.setPassword("asule");
        mMybatiesService.save(testUser);
    }
    @RequestMapping("update")
    public  void  update(){
        TestUser testUser = new TestUser();
        testUser.setId("0519");
        testUser.setUsername("asule1");
        testUser.setPassword("asule1");
        mMybatiesService.update();
    }
    @RequestMapping("list")
    @ResponseBody
    public PageUtils list(){
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("limit","3");
        PageUtils page=mMybatiesService.queryPage(map);
        log.info("aaaaaa");
        return page;
    }
    @RequestMapping("list2")
    @ResponseBody
    public  PageUtils list2(){
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("limit","3");
        PageUtils page=mMybatiesService2.queryPage(map);
        return page;
    }
    @RequestMapping("test")
    public  void test() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
            PageUtils pageUtils = list();
                List<TestUser> list = (List<TestUser>) pageUtils.getList();
                 long startTime = System.currentTimeMillis();
                 int num=6;
                int n=list.size();
                System.out.println(n);
                int page=(n/num)+1;
                for (int i=1;i<=page;i++){
                    List<TestUser> list0;
                    if(i!=page){
                        list0=new ArrayList(list.subList((i-1)*num, (i*num)));
                    }else{
                        list0=new ArrayList(list.subList((i-1)*num, n));
                    }
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<TestUser> lista=depCopy(list0);
                        System.out.println(lista+"---"+lista.size());
                    }
                });
                   /* ExecutorService executorService = Executors.newFixedThreadPool(5);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });*/
                }
            long endTime = System.currentTimeMillis();
             System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
    @RequestMapping("test2")
    public  void test2() {
        long startTime = System.currentTimeMillis();
        for (int i=0;i<5;i++){
            PageUtils pageUtils = list2();
            System.out.println(pageUtils);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("twotable")
    @ResponseBody
    public PageUtils twotable(){
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("limit","3");
        IPage<AgeUser> ageUserPage = mMybatiesService.selectTwo(map);
        log.info("aaaaaa");
        return new PageUtils(ageUserPage);
    }
    @RequestMapping("twotable1")
    @ResponseBody
    public PageUtils twotable1(){
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("limit","3");
        PageUtils page=mMybatiesService.queryPage1(map);
        log.info("aaaaaa");
        return page;
    }

    @RequestMapping("sql1")
    @ResponseBody
    public PageUtils sql1(){
        Map<String, Object> map = new HashMap<>();
        map.put("page","1");
        map.put("limit","3");
        int id=1;
        IPage<TestUser> testUserIPage= mMybatiesService.sql1(map,id);
        return new PageUtils(testUserIPage);
    }
    @RequestMapping("QueryWrapper")
    @ResponseBody
    public PageUtils QueryWrapper(Long page,Long limit){
        Map map = new HashMap();
        map.put("page",page);
        map.put("limit",limit);
        IPage<TestUser> testUserIPage= mMybatesQueryWrapper.QueryWrapper(map);
        return new PageUtils(testUserIPage);
    }

}
