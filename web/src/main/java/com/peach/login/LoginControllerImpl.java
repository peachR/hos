package com.peach.login;

import com.peach.domain.entity.User;
import com.peach.service.redis.RedisService;
import com.peach.service.test.TestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peach.domain.response.Result;

@RestController
@RequestMapping("/login")
public class LoginControllerImpl implements LoginController {
    private static final Logger log = LogManager.getLogger(LoginControllerImpl.class);

    @Autowired
    private TestService testService;

    @Autowired
    private RedisService redisService;

    @GetMapping("")
    public Result login(User user) {
        return Result.newSuccessResult(redisService.get("test"));
    }

    @Override
    @GetMapping("/test")
    public Result getUserInformation() {
        log.error("test error log success");
        log.warn("test warn log success");
        log.info("test info log success");
        redisService.set("test", "application set");
        return Result.newSuccessResult(testService.getUserByNumber("123"));
    }
}
