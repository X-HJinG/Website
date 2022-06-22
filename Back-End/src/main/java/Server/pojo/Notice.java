package Server.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "notice")
public class Notice {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer nid;
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadtime;
    private String mainContext;
    private String remark;
    private UserInfo userInfo;
}
