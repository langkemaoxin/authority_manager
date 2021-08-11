package com.imooc.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.dto.LoginDto;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);
}
