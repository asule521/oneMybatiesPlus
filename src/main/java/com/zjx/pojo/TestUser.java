package com.zjx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

@Data
@TableName("test_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type= IdType.INPUT)
    private String id;

    private String username;

    private String password;
}
