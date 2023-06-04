package com.abin.mallchat.common.user.service.cache;


import com.abin.mallchat.common.user.dao.UserDao;
import com.abin.mallchat.common.user.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

class UserCacheTest extends BaseSpringBootTest{

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserDao userDao;

    @Test
    void online() {
        List<User> list = userDao.list();
        for(User user:list){
            userCache.online(user.getId(),new Date());
        }

    }
}