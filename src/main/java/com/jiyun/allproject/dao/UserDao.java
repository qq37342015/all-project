package com.jiyun.allproject.dao;

import com.jiyun.allproject.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    // 根据账号密码查询
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);
}
