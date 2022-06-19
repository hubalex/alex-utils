package com.alex.utils.ctrl;

import com.alex.utils.annotation.Decrypt;
import com.alex.utils.annotation.Encrypt;
import com.alex.utils.entity.RespDTO;
import com.alex.utils.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Title: TestController
 * @Author: zjt
 * @CreateTime: 2022/6/19 13:53
 */

@RestController
public class TestController {

    @GetMapping("/user")
    @Encrypt
    public RespDTO getUser() {
        User user = new User();
        user.setId((long) 99);
        user.setUsername("javaboy");
        return RespDTO.ok("ok", user);
    }

    @PostMapping("/user")
    public RespDTO addUser(@RequestBody @Decrypt User user) {
        System.out.println("user = " + user);
        return RespDTO.ok("ok", user);
    }
}
