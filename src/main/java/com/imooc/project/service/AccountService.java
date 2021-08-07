package com.imooc.project.service;

import com.imooc.project.dto.LoginDto;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author rexchen
 * @since 2021-08-07
 */
public interface AccountService extends IService<Account> {
    LoginDto login(String username, String password);
}
