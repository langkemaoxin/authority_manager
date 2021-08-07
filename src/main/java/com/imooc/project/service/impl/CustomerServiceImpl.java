package com.imooc.project.service.impl;

import com.imooc.project.entity.Customer;
import com.imooc.project.mapper.CustomerMapper;
import com.imooc.project.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
