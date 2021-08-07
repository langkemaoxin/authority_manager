package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RoleResource对象", description="角色资源表")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "role_resource_id", type = IdType.AUTO)
    private Long roleResourceId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;


}
