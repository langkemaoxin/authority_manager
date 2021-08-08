package com.imooc.project.controller;

import com.imooc.project.dto.LoginDto;
import com.imooc.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AccountService accountService;

    /**
     * @param username
     * @param password
     * @param httpSession 在controller层，引入session，用于成功后写入session
     * @param attributes  失败时，引用RedirectAttributes，重定向属性，用于写入错误信息
     * @return
     * @PostMapping 注解用来声明一个请求
     */
    @PostMapping("login")
    public String login(String username, String password, HttpSession httpSession, RedirectAttributes attributes) {

        LoginDto loginDto = accountService.login(username, password);

        String error = loginDto.getError();

        if (error == null) {
            httpSession.setAttribute("account", loginDto.getAccount());
        } else {

            //使用这种方式，在重定向后再url中增加参数，暴露了参数，
            //attributes.addAttribute()

            //但是通过这种方式，把参数隐藏到了session中，并且在重定向后，就自动移除了
            attributes.addFlashAttribute("error", error);
        }

        return loginDto.getPath();
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {

        //
        //session.removeAttribute("account");

        //直接销毁session
        session.invalidate();

        /**
         *
         * 这里有个疑问：
         * 1 什么时候使用 模板路径 login/login
         * 2 什么时候使用 视图名称
         *
         *
         */

        //return "/"; ==> 如果使用这种方式，则发现URL地址直接就是 auth/logout,这是个方法名称，而不是模板方法
         //return "login/login";
         return "redirect:/";
    }
}
