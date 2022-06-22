package Server.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "department")
public class Department {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer dNo;
    private String dName;
}
