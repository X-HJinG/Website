package Server.service;

import Server.mapper.ServiceMapper;
import Server.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommonService {
    @Autowired
    ServiceMapper serviceMapper;

    public List<Notice> getNotice(){
        return serviceMapper.getNotice();
    }
}
