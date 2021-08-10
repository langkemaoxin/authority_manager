package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import com.imooc.project.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("toList")
    public String toList() {

        //返回模板
        return "customer/customerList";
    }


    @GetMapping("toAdd")
    public String toAdd() {

        //返回模板
        return "customer/customerAdd";
    }



    /**
     * 查询方法
     * @param realName
     * @param phone
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {

        LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery()
                .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId);

        Page<Customer> myPage = customerService.page(new Page<>(page, limit), wrapper);

        //直接使用lambdaQuery进行查询
        Page<Customer> page1 = customerService.lambdaQuery().like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId)
                .page(new Page<>(page, limit));


        return ResultUtil.buildPageR(page1);

//
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("count", myPage.getTotal());
//        data.put("records", myPage.getRecords());
//
//        return R.ok(data);
    }

    @PostMapping("add")
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer){

        return ResultUtil.buildR(customerService.save(customer));
    }


    /**
     * 进入修改页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){

        Customer customer = customerService.getById(id);

        model.addAttribute("customer",customer);

        return "customer/customerUpdate";
    }

    /**
     * 修改的主方法
     *
     * 1、使用PutMapping RestFul风格
     * 2、使用updateById mybatis的方法
     * 3、添加注解@ResponseBody 放在方法的前面
     * 4、接收一个实体的注解 @RequestBody
     * @param customer
     * @return
     */
    @PutMapping("update")
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer){
        return ResultUtil.buildR(customerService.updateById(customer));
    }
}
