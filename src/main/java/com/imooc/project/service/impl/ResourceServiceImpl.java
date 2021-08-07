package com.imooc.project.service.impl;

import com.imooc.project.entity.Resource;
import com.imooc.project.mapper.ResourceMapper;
import com.imooc.project.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
