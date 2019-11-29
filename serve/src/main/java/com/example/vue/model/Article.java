package com.example.vue.model;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer id;
    private String title;
    private String imageUrl;
    private String html;
    private String markdown;
    private Date createTime;
    private Date modifiedTime;
}
