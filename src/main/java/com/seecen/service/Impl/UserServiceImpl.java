package com.seecen.service.Impl;

import com.seecen.dao.UserDao;
import com.seecen.entity.User;
import com.seecen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }
}
