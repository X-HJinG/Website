package Server.controller;

import Server.pojo.Account;
import Server.pojo.Notice;
import Server.pojo.Result;
import Server.service.UserService;
import Server.utils.ResultEnum;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class notice {
    @Autowired
    UserService userService;


    @PostMapping("/api/notice/input")
    public Result setContext(@RequestBody Notice notice){
        int i= userService.createContext(notice);
        return new Result(i, ResultEnum.SUCCESS);

    }

    @PostMapping("/api/notice/output")
    public Result getContext(@RequestBody Account account){
        List<Notice> contexts = userService.getContext(account.getUserId());
        return new Result(contexts, ResultEnum.SUCCESS);
    }

    @PostMapping("/api/notice/delete")
    public Result getContext(@RequestBody Notice notice){
        System.out.println(notice);
        int i = userService.deleteContext(notice.getNid());
        return new Result(i, ResultEnum.SUCCESS);
    }

    @PostMapping("/api/notice/update")
    public Result updateContext(@RequestBody Notice notice){
        System.out.println(notice);
        int i = userService.updateContext(notice);
        if(i>0)
            return new Result(i,ResultEnum.SUCCESS);
        else
            return new Result(null,ResultEnum.FAIL);
    }
}
