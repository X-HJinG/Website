package Server.controller;

import Server.mapper.AdminMapper;
import Server.pojo.Account;
import Server.pojo.Result;
import Server.pojo.UserInfo;
import Server.service.AdminService;
import Server.service.UserService;
import Server.utils.JwtUtil;
import Server.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class register {

    @Autowired
    AdminService adminService;

    @PostMapping("/api/register")
    public Result insertAccount(@RequestBody List<Map<String,Object>> List){
        Result result;
        List<UserInfo> userInfoList = new LinkedList<>();
        List<Account> accountList = new LinkedList<>();
        for (Map<String, Object> map : List) {
            Account account = new Account();
            UserInfo userInfo;
            account.setUserId((String)map.get("userId"));
            account.setUserName((String) map.get("userName"));
            account.setPassword((String) map.get("password"));
            userInfo = new UserInfo(account);
            userInfo.setDNo((Integer) map.get("dNo"));
            accountList.add(account);
            userInfoList.add(userInfo);
        }
        int count1 =  adminService.createAccounts(accountList);
        int count2 =  adminService.createMemberInfos(userInfoList);
        if(count1>0&&count2>0){
            result =new Result(null,ResultEnum.SUCCESS);
        }
        else {
            result =new Result(null,ResultEnum.FAIL);
        }
        return result;
    }

}

