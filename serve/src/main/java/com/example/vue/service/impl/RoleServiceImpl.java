package com.example.vue.service.impl;

import com.example.vue.model.Role;
import com.example.vue.mapper.RoleMapper;
import com.example.vue.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author YHG
 * @since 2019-12-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
