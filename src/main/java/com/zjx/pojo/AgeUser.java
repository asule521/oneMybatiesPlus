package com.zjx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
@Data
public class AgeUser implements Serializable {

        private static final long serialVersionUID = 1L;
        @TableId(type= IdType.INPUT)
        private String id;

        private String name;

        private int age;

        private String remark;

        private String username;
}

