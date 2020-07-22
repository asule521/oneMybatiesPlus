package com.zjx.controller;

public class MyThread implements Runnable{

    private  String name;
    private  int num;
    public  MyThread(String name,int num){
        this.name=name;
        this.num=num;
    }
    @Override
    public void run() {
        try {
            for (int i=0;i<num;i++){
                System.out.println(name+"正在执行");
            }
        }catch (Exception e){
            System.out.println("线程执行异常");
        }


    }
}
