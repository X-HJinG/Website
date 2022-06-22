package Server.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "account")
public class Account {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String userId;
    private String password;
    private String userName;
}
