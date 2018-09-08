package com.peach.dao.test;

import org.apache.ibatis.annotations.Param;
import com.peach.domain.entity.User;

public interface TestDao {
    public User getUserByNumber(@Param("number") String number);
}
