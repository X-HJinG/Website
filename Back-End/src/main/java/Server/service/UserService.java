package Server.service;

import Server.mapper.UserMapper;
import Server.pojo.Account;
import Server.pojo.Notice;
import Server.pojo.Record;
import Server.pojo.UserInfo;
import org.mockito.internal.matchers.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String queryUserById(Account account) {
        return userMapper.userCheck(account);
    }

    public String queryAdminById(Account account){
        return userMapper.leaderCheck(account);
    }

    public UserInfo getInfo(String userId){
        return userMapper.getInfo(userId);
    }

    public int updateInfo(UserInfo userInfo) {
        return userMapper.updateInfo(userInfo);
    }

    public int updateMemberPassword(Account account) {
        return userMapper.updateMemberPassword(account);
    }

    public int createContext(Notice notice){
        return userMapper.createContext(notice);
    }

    public List<Notice> getContext(String userId){
        return userMapper.getContext(userId);
    }

    public int updateContext(Notice notice){
        return userMapper.updateContext(notice);
    }

    public int deleteContext(Integer nid){
        return userMapper.deleteContext(nid);
    }

    public List<Record> getRecords(String userId){
        return userMapper.getRecords(userId);
    }
}
