package com.srg.exchange.service;

import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.User;

/**
 * Created by srg
 *
 * @date 2018/5/21
 */
public interface IUserService {

    ServerResponse<User> login(User user);

    ServerResponse<User> detail(String userId);

    ServerResponse<User> auth(User user);
}
