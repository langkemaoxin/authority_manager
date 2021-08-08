package com.imooc.project.service;

import com.imooc.project.dto.ResourceVO;
import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据角色ID获取资源
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);
}
