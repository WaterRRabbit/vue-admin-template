package com.example.vue.service.impl;

import com.example.vue.model.User;
import com.example.vue.mapper.UserMapper;
import com.example.vue.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author YHG
 * @since 2019-12-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public boolean save(User entity) {
        entity.setCreateTime(LocalDateTime.now());
        entity.setModifiedTime(LocalDateTime.now());
        return super.save(entity);
    }
}
