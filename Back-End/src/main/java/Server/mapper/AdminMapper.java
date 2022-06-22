package Server.mapper;

import Server.pojo.Account;
import Server.pojo.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    public int createAccounts(List<Account> list);
    public int createMemberInfos(List<UserInfo> list);
    public List<UserInfo> getAllMemberInfo(UserInfo userInfo);
    public int deleteMember(String userId);
    public int resetPassword(String userId);
    public int updateLeaderPassword(Account account);
}
