package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.imooc.project.dto.ResourceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.project.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询当前登录人的资源
     *
     * 1、@Param(Constants.WRAPPER) Wrapper<Resource> wrapper 这自定义sql的资源，为什么要这么做？
     * 有什么好处
     *
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);
}
