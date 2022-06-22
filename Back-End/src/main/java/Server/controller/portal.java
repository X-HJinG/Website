package Server.controller;

import Server.pojo.Notice;
import Server.pojo.Result;
import Server.service.CommonService;
import Server.utils.ResultEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;



@RestController
public class portal {
    @Autowired
    CommonService commonService;

    @GetMapping("/api/getNotice")
    public Result getNotice(Integer pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Notice> notice = commonService.getNotice();
        PageInfo<Notice> info = new PageInfo<>(notice);
        HashMap<String, Object> result = new HashMap<>();
        result.put("list",info.getList());
        result.put("total",info.getTotal());
        return new Result(result, ResultEnum.SUCCESS);
    }
}
