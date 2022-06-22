package Server.service;

import Server.mapper.AdminMapper;
import Server.mapper.UserMapper;
import Server.pojo.Account;
import Server.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    public int createAccounts(List<Account> list) {
        return adminMapper.createAccounts(list);
    }

    public int createMemberInfos(List<UserInfo> list) {
        return adminMapper.createMemberInfos(list);
    }


    public List<UserInfo> getAllMemberInfo(UserInfo userInfo) {
        return adminMapper.getAllMemberInfo(userInfo);
    }

    public int deleteMember(String userId) {
        return adminMapper.deleteMember(userId);
    }

    public int resetPassword(String userId) {
        return adminMapper.resetPassword(userId);
    }

    public int updateLeaderPassword(Account account) {
        return adminMapper.updateLeaderPassword(account);
    }
}
