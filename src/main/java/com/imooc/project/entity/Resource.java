package com.imooc.project.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Resource对象", description="资源表")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long resourceId;

    @ApiModelProperty(value = "父id")
    private Long parentId;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源类型(0、目录 1、菜单 2、按钮)")
    private Integer resourceType;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "权限标识码")
    private String code;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
