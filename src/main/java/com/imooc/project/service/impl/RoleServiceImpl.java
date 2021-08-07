package com.imooc.project.service.impl;

import com.imooc.project.entity.Role;
import com.imooc.project.mapper.RoleMapper;
import com.imooc.project.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
