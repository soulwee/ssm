package com.seecen.dao;

import com.seecen.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> queryAllUser();
}
