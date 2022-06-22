package Server.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="userInfo")
public class UserInfo {
    @Id
    @KeySql(useGeneratedKeys = true)
    //账号
    private String userId;
    //用户名
    private String userName;
    //个人联系方式
    private String userTel;
    //学号
    private String userNo;
    //专业编号
    private Integer mNo;
    //部门编号
    private Integer dNo;
    //类型
    private Boolean type;

    public UserInfo(){
    }

    public UserInfo(Account account){
        this.userId = account.getUserId();
        this.userName = account.getUserName();
    }
}
