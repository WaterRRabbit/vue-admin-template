package com.example.vue.controller;

import com.example.vue.common.Result;
import com.example.vue.model.Article;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("article")
public class ArticleController {

    @GetMapping("/get/{id}")
    public Result getArticle(@PathVariable Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setTitle("第一篇文章");
        article.setHtml("<h1>Vue</h1>");
        article.setMarkdown("# Vue");
        article.setImageUrl("www.yehaigang.cn");
        article.setCreateTime(new Date());
        article.setModifiedTime(new Date());
        return Result.builder().code(20000).message("success").data(article).build();
    }

    @PostMapping("/publish")
    public Result publish(@RequestBody Article article) {
        System.out.println(article);
        return Result.builder().code(20000).message("success").data(article.getId()).build();
    }

    @PostMapping("/draft")
    public Result draft(@RequestBody Article article) {
        System.out.println(article);
        return Result.builder().code(20000).message("success").data(article.getId()).build();
    }
}
