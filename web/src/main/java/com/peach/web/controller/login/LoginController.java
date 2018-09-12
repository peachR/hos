package com.peach.web.controller.login;

import com.peach.domain.entity.User;
import com.peach.domain.response.Result;


public interface LoginController {
    Result login(User user);

    Result getUserInformation();

    Result exceptionTest();
}
