package Server.controller;

import Server.pojo.Account;
import Server.pojo.Result;
import Server.pojo.UserInfo;
import Server.service.AdminService;
import Server.service.UserService;
import Server.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class changeInfo {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @PostMapping("/api/Info")
    public Result updateInfo(@RequestBody UserInfo userInfo){
        int i = userService.updateInfo(userInfo);
        return new Result(i, ResultEnum.SUCCESS);
    }

    @PostMapping("/api/security/0/check")
    public Result checkAccount(@RequestBody Account account){
        if (userService.queryUserById(account)!=null){
            return new Result(null,ResultEnum.SUCCESS);
        }
        return new Result(null,ResultEnum.FAIL);
    }

    @PostMapping("/api/security/1/check")
    public Result checkAdminAccount(@RequestBody Account account){
        if (userService.queryAdminById(account)!=null){
            return new Result(null,ResultEnum.SUCCESS);
        }
        return new Result(null,ResultEnum.FAIL);
    }

    @PostMapping("/api/security/0")
    public Result changeMemberPassword(@RequestBody Account account){
        int i = userService.updateMemberPassword(account);
        return new Result(i,ResultEnum.SUCCESS);
    }


    @PostMapping("/api/security/1")
    public Result changeLeaderPassword(@RequestBody Account account){
        int i = adminService.updateLeaderPassword(account);
        return new Result(i,ResultEnum.SUCCESS);
    }

}
