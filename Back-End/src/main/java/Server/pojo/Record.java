package Server.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Record {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date historyDate;
    private String address;
    private String remark;
    private Integer status;
}
