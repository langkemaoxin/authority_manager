package com.imooc.project.dto;

import lombok.Data;

import java.util.List;

/**
 * 这里其实就是一个菜单的用法
 *
 * 资源名称，ID，地址
 *
 */
@Data
public class ResourceVO {


    private Long resourceId;

    private String resourceName;

    private String url;

    /**
     * 下级资源
     */
    private List<ResourceVO> subs;
}
