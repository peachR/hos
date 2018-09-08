package com.peach.service.test.impl;

import com.peach.dao.test.TestDao;
import com.peach.domain.entity.User;
import com.peach.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    public User getUserByNumber(String number) {
        return testDao.getUserByNumber(number);
    }
}
