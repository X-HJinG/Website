package Server.pojo;

import Server.utils.ResultEnum;

public class Result {
    public class Meta{
        public Integer code;
        public String msg;
        public String token;
    }
    public Meta meta = new Meta();
    public Object data ;

    public Result() {
    }

    public Result(Object data,ResultEnum resultEnum) {
        this.data = data;
        this.meta.code = resultEnum.code;
        this.meta.msg = resultEnum.msg;
    }
}
