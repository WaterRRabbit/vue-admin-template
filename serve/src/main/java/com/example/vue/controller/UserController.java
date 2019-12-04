package com.example.vue.controller;

import com.example.vue.common.Result;
import com.example.vue.model.User;
import com.example.vue.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println(user.getUsername() + ":" + user.getPassword());
        Map<String, Object> data = new HashMap<>(1);
        data.put("token", "admin-token-something");
        return Result.builder().code(20000).message("success").data(data).build();
    }

    @GetMapping("/info")
    public Result info(String token) {
        System.out.println("token:" + token);
        Map<String, Object> data = new HashMap<>(2);
        data.put("name", "admin");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.builder().code(20000).message("success").data(data).build();
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.builder().code(20000).message("success").data("success").build();
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        System.out.println("Add " + user);
        userService.save(user);
        return Result.builder().code(20000).message("success").data(user.getUserId()).build();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        System.out.println("Update " + user);
        return Result.builder().code(20000).message("success").build();
    }

    @PostMapping("/del")
    public Result delete(@RequestBody User user){
        System.out.println("Delete User " + user.getUserId());
        return Result.builder().code(20000).message("success").build();
    }

    @GetMapping("/list")
    public Result list(Integer pageNum, Integer pageSize) {
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("total", 30);
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = pageNum * pageSize; i <= pageNum * pageSize + pageSize; i++) {
            User user = new User();
            user.setUserId(10000L);
            user.setUsername("走你");
            user.setCreateTime(LocalDateTime.now());
            user.setModifiedTime(LocalDateTime.now());
            user.setStatus(random.nextInt(3));
            users.add(user);
        }
        data.put("items", users);
        return Result.builder().code(20000).message("success").data(data).build();
    }
}
