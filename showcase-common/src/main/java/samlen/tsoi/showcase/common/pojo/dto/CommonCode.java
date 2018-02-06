package samlen.tsoi.showcase.common.pojo.dto;

/**
 * @author samlen_tsoi
 * @date 2018/1/19
 */
public enum CommonCode implements Code {

    SUCCESS(Integer.valueOf(0), "成功"),
    FAIL(Integer.valueOf(-1), "处理失败"),
    UNKNOWN(Integer.valueOf(-2), "未知错误"),
    NULL_EXCEPTION(Integer.valueOf(-3), "空指针异常"),
    NOT_LOGIN_IN(Integer.valueOf(-4), "未登录"),
    PARAM_ERROR(Integer.valueOf(-5), "参数错误"),
    NOT_AUTH_REQUEST(Integer.valueOf(-6), "非法请求"),
    FILE_UPLOAD_ERR(Integer.valueOf(-7), "文件为空"),
    REQUEST_METHOD_ERR(Integer.valueOf(-8), "http方法不适用"),
    REQUEST_CONTENTYPE_ERR(Integer.valueOf(-9), "content type错误"),
    EXISTS_DATA(Integer.valueOf(-10), "数据已存在"),
    NOT_PERMISSIONS(Integer.valueOf(-11), "没有权限"),
    NOT_EXISTS_RESOURCE(Integer.valueOf(-12), "资源不存在"),
    RPC_ERROR(Integer.valueOf(-13), "RPC链接错误"),
    NO_TOKEN_PARAM(Integer.valueOf(-14), "缺失TOKEN"),
    TOKEN_NOT_EXISTS(Integer.valueOf(-15), "TOKEN超时");

    private Integer code;
    private String message;

    private CommonCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.message;
    }
}
