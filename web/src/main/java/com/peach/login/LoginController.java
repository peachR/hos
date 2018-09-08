package com.peach.login;

import com.peach.domain.entity.User;
import response.Result;


public interface LoginController {
    Result login(User user);

    Result getUserInformation();
}
