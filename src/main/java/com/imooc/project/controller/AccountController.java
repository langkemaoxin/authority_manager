package com.imooc.project.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.service.AccountService;
import com.imooc.project.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 请求返回模板
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "account/accountList";
    }


    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(Long page, Long limit) {

        Page<Account> accountPage = accountService.lambdaQuery()
                .orderByDesc(Account::getCreateTime)
                .page(new Page<>(page, limit));

        return ResultUtil.buildPageR(accountPage);
    }


    /**
     * 返回添加的模板
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "account/accountAdd";
    }

    /**
     * 添加客户
     * @param account
     * @return
     */
    @PostMapping("Add")
    @ResponseBody
    public R<Object> add(@RequestBody Account account) {
        return ResultUtil.buildR(accountService.save(account));
    }


    /**
     * 返回添加的模板
     *
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {

        Account account = accountService.getById(id);
        model.addAttribute("account",account);

        return "account/accountDetail";
    }


    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {

        Account account = accountService.getById(id);
        model.addAttribute("account",account);

        return "account/accountUpdate";
    }

    /**
     * 更新用户信息
     * @param account
     * @return
     */
    @PutMapping("update")
    public R<Object> update(@RequestBody Account account){
        return ResultUtil.buildR(accountService.save(account));
    }


    @DeleteMapping("/{id}")
    public R<Object> delete(@PathVariable Long id){
        return ResultUtil.buildR(accountService.removeById(id));
    }

}
