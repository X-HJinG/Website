package Server.controller;

import Server.pojo.Account;
import Server.pojo.Result;
import Server.pojo.UserInfo;
import Server.service.UserService;
import Server.utils.JwtUtil;
import Server.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class login {
    @Autowired
    UserService userService;


    @RequestMapping("/api/login")
    public Result loginAPI(@RequestBody Account account) throws IOException, IllegalAccessException {
        String userId;
        Result result;
        UserInfo userInfo = null;
        boolean flag = false;
        if ((userId = userService.queryAdminById(account)) != null) {
            userInfo = userService.getInfo(userId);
            userInfo.setType(true);
            flag = true;
        } else if ((userId = userService.queryUserById(account)) != null) {
            userInfo = userService.getInfo(userId);
            userInfo.setType(false);
            flag = true;
        }
        if (flag) {
            String token = JwtUtil.createToken(userInfo);
            result = new Result(userInfo, ResultEnum.SUCCESS);
            result.meta.token = token;
            return result;
        }
        result = new Result(null, ResultEnum.FAIL);
        return result;
    }

}
