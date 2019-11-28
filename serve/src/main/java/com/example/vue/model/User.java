package com.example.vue.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String salt;
    private Integer state;
    private Date createTime;
    private Date modifiedTime;

}
