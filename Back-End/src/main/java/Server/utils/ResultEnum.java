package Server.utils;

public enum ResultEnum {
    SUCCESS(200, "success"),
    FAIL(403,"fail"),
    OUTIME(4031,"out-time"),
    PERMISSION_DENIED(4032,"permission_denied");


    public Integer code;
    public String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
