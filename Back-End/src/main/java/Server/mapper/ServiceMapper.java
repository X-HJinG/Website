package Server.mapper;

import Server.pojo.Notice;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ServiceMapper {
    public List<Notice> getNotice();
}
