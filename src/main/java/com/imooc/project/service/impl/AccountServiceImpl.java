package com.imooc.project.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.imooc.project.dto.LoginDto;
import com.imooc.project.entity.Account;
import com.imooc.project.mapper.AccountMapper;
import com.imooc.project.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public LoginDto login(String username, String password) {

        /**
         *
         * 这里使用了重定向，也就是页面URL不发生变动的情况
         *
         */

        LoginDto dto = new LoginDto();
        dto.setPath("redirect:/");

        Account account = lambdaQuery().eq(Account::getUsername, username).one();

        if (account == null) {
            dto.setError("用户名不存在");
            return dto;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if(!digestHex.equalsIgnoreCase(account.getPassword())){
            dto.setError("密码错误");
            return dto;
        }

        dto.setPath("login/main");
        dto.setAccount(account);

        return dto;
    }
}
