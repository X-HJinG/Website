package Server.controller;

import Server.pojo.Record;
import Server.pojo.Result;
import Server.service.UserService;
import Server.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class record {
    @Autowired
    UserService userService;

    @PostMapping("/api/record")
    public Result getRecords(@RequestParam String userId){
        List<Record> records = userService.getRecords(userId);
        return new Result(records, ResultEnum.SUCCESS);
    }
}
