package Server.mapper;

import Server.pojo.Account;
import Server.pojo.Notice;
import Server.pojo.Record;
import Server.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper{
    public String userCheck(Account account);
    public String leaderCheck(Account account);
    public UserInfo getInfo(String userid);
    public int updateInfo(UserInfo userInfo);
    public int updateMemberPassword(Account account);
    public int createContext(Notice notice);
    public List<Notice> getContext(String userId);
    public int updateContext(Notice notice);
    public int deleteContext(Integer nid);
    public List<Record> getRecords(String userId);
}
