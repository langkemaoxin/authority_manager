package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.dto.ResourceVO;
import com.imooc.project.entity.Resource;
import com.imooc.project.mapper.ResourceMapper;
import com.imooc.project.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {

        /**
         * 有别名，用普通的条件构造器？
         *
         * 泛型的条件构造器，其实就是lambdaquery.eq 什么的
         * 但是只能针对单表进行操作
         *
         * 面对这种复杂操作的时候，就会非常麻烦，所以可以使用这种自定义的构造器进行构造 查询条件
         *
         */


        /**
         * 声明一个自定义构造器，然后在数据库中进行查询
         */
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id",roleId).isNull("re.parent_id");
        List<ResourceVO> resourceVOS = baseMapper.listResource(query);

        //分别遍历每一个父级，获取子级，这里的情况仅仅是针对只有一级子级的情况
        resourceVOS.forEach(r->{

            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.query();
            subWrapper.eq("rr.role_id",roleId)
                    .eq("re.parent_id",resourceId);

            //获取子级的菜单
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);

            if(CollectionUtils.isNotEmpty(subResourceVOS)){
                r.setSubs(subResourceVOS);
            }
        });

        return resourceVOS;
    }
}
