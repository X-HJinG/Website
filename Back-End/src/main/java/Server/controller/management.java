package Server.controller;

import Server.mapper.AdminMapper;
import Server.pojo.Account;
import Server.pojo.Result;
import Server.pojo.UserInfo;
import Server.service.AdminService;
import Server.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class management {
    @Autowired
    AdminService adminService;

    @PostMapping("/api/management/getList")
    public Result getAllMembers(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        Result result;
        List<UserInfo> allMemberInfos = adminService.getAllMemberInfo(userInfo);
        result = new Result(allMemberInfos, ResultEnum.SUCCESS);
        return result;
    }

    @PostMapping("/api/management/delete")
    public Result deleteMember(@RequestParam(name = "userId") String userId) {
        System.out.println(userId);
        int i = adminService.deleteMember(userId);
        return new Result(i, ResultEnum.SUCCESS);
    }

    @PostMapping("/api/management/reset")
    public Result reset(@RequestParam(name = "userId") String userId) {
        Result result;
        int i = adminService.resetPassword(userId);
        if (i > 0) result = new Result(i, ResultEnum.SUCCESS);
        else result = new Result(null, ResultEnum.FAIL);
        return result;
    }
}
