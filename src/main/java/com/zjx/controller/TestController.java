package com.zjx.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import org.springframework.boot.convert.Delimiter;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestController {



    public static void main(String[] args) {
            methon2();

    }
    public static  void methon(){
        Thread t =Thread.currentThread();
        t.setName("单例线程");
        //有1000条数据需要更新，由一个人来完成
        for(int i=0;i<1000;i++){
            try {
                System.out.println(t.getName()+"正在执行"+i);
                //t.sleep(500);  //线程休眠中
            } catch (Exception e) {
                System.out.println("线程出现错误了！！");
            }
        }
    }
    public static void methon1(){
        MyThread myThread = new MyThread("第一个线程",50);
        MyThread myThread2 = new MyThread("第二个线程",50);
        MyThread myThread3 = new MyThread("第三个线程",50);
        MyThread myThread4 = new MyThread("第四个线程",50);
        Thread thread = new Thread(myThread);
        Thread thread2 = new Thread(myThread2);
        Thread thread3 = new Thread(myThread3);
        Thread thread4 = new Thread(myThread4);
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    //guava使用
    public static void methon2(){
        List<String> lists = Lists.newArrayList("a", null, "b", "g", "8", "9");
        String result = Joiner.on(",").skipNulls().join(lists);
        log.info(result);

    }
}
