package com.srg.exchange.service.Impl;

import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.dao.UserMapper;
import com.srg.exchange.pojo.User;
import com.srg.exchange.service.IUserService;
import com.srg.exchange.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by srg
 *
 * @date 2018/5/21
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(User user) {
        User user1 = userMapper.selectByUserId(user.getUserId());
        if(user1 == null){
            System.out.println("login user1 == null");
            int result = userMapper.insert(user);
            if(result == 0){
                return ServerResponse.createByErrorMessage("登录失败");
            }
            return ServerResponse.createBySuccess("登录成功",user);
        }
        if(!user.getAvatarurl().equals(user1.getAvatarurl())  || !user.getUsername().equals(user1.getUsername()) ){
            System.out.println("login user != user1");
            int result = userMapper.updateInfoByUserId(user);
            if(result == 0){
                return ServerResponse.createByErrorMessage("登录失败");
            }
            User user2 = userMapper.selectByUserId(user.getUserId());
            if(user2 != null){
                return ServerResponse.createBySuccess(user2);
            }
            return ServerResponse.createByErrorMessage("登录失败");
        }
        System.out.println("login user1 != null");
        return ServerResponse.createBySuccess("登录成功",user1);
    }

    @Override
    public ServerResponse<User> detail(String userId){
        User user = userMapper.selectByUserId(userId);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    @Override
    public ServerResponse<User> auth(User user){
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result == 0){
            return ServerResponse.createByErrorMessage("认证失败");
        }
        User user1 = userMapper.selectByUserId(user.getUserId());
        if(user1 != null){
            return ServerResponse.createBySuccess(user1);
        }
        return ServerResponse.createByErrorMessage("认证失败");
    }

}
