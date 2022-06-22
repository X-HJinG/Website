package Server.controller;


import Server.pojo.Account;
import Server.pojo.Result;
import Server.utils.CommonUtil;
import Server.utils.JwtUtil;
import Server.utils.ResultEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class getResources {
    @GetMapping("/api/resources")
    public void getMenuList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("token");
        if(JwtUtil.isAdmin(token)){
            response.sendRedirect("/static/json/adminList.json");
        }
        else{
            response.sendRedirect("/static/json/memberList.json");
        }
    }

    @PostMapping("/api/register/output")
    public Result getFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Reader reader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(reader);
        Result result;
        LinkedList<Map<String,Object>> list = new LinkedList<>();
        String str = "";
        while((str=br.readLine())!=null&& !str.equals("")){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("userName",str);
            map.put("userId", CommonUtil.createId());
            map.put("password","123456");
            list.addLast(map);
        }
        result = new Result(list,ResultEnum.SUCCESS);
        return result;
    }

    @PostMapping("/api/register/getList")
    public StringBuilder getAccountList(@RequestBody List<Account> accountList) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("姓名,账号,密码\n");
        for (Account account : accountList) {
            result.append(account.getUserName()).append(",")
                    .append(account.getUserId()).append(",")
                    .append(account.getPassword()).append("\n");
        }
        return result;
    }
}
