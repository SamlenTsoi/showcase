package samlent.tsoi.showcase.core.dto;

import lombok.AllArgsConstructor;

/**
 * @author samlen_tsoi
 * @date 2018/1/19
 */
@AllArgsConstructor
public enum CommonCode implements Code {
    /**
     * 成功
     */
    SUCCESS(Integer.valueOf(0), "成功"),

    /**
     * 失败
     */
    FAIL(Integer.valueOf(-1), "处理失败"),

    /**
     * 未知错误
     */
    UNKNOWN(Integer.valueOf(-2), "未知错误"),

    /**
     * 空指针异常
     */
    NULL_EXCEPTION(Integer.valueOf(-3), "空指针异常"),

    /**
     * 未登录
     */
    NOT_LOGIN_IN(Integer.valueOf(-4), "未登录"),

    /**
     * 参数错误
     */
    PARAM_ERROR(Integer.valueOf(-5), "参数错误"),

    /**
     * 非法请求
     */
    NOT_AUTH_REQUEST(Integer.valueOf(-6), "非法请求"),

    /**
     * 文件为空
     */
    FILE_UPLOAD_ERR(Integer.valueOf(-7), "文件为空"),

    /**
     * http方法不适用
     */
    REQUEST_METHOD_ERR(Integer.valueOf(-8), "http方法不适用"),

    /**
     * content type错误
     */
    REQUEST_CONTENTYPE_ERR(Integer.valueOf(-9), "content type错误"),

    /**
     * 数据已存在
     */
    EXISTS_DATA(Integer.valueOf(-10), "数据已存在"),

    /**
     * 没有权限
     */
    NOT_PERMISSIONS(Integer.valueOf(-11), "没有权限"),

    /**
     * 资源不存在
     */
    NOT_EXISTS_RESOURCE(Integer.valueOf(-12), "资源不存在"),

    /**
     * RPC链接错误
     */
    RPC_ERROR(Integer.valueOf(-13), "RPC链接错误"),

    /**
     * 缺失TOKEN
     */
    NO_TOKEN_PARAM(Integer.valueOf(-14), "缺失TOKEN"),

    /**
     * TOKEN超时
     */
    TOKEN_NOT_EXISTS(Integer.valueOf(-15), "TOKEN超时");

    /**
     * 状态吗
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.message;
    }
}
