package cn.yangsansui.service.impl;

import cn.yangsansui.mapper.UserMapper;
import cn.yangsansui.pojo.User;
import cn.yangsansui.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLogin(String userCode, String userPassword) {
        return userMapper.userLogin(userCode,userPassword);
    }

    @Override
    public List<User> allUser(String userName) {
        if(userName.length()<=0){
            userName=null;
        }
        return userMapper.allUser(userName);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User userById(Integer id) {
        return userMapper.userById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer delUser(Integer id) {
        return userMapper.delUser(id);
    }

    @Override
    public Integer updatePassWord(Integer id, String newPassWord) {
        return userMapper.updatePassWord(id,newPassWord);
    }

}
