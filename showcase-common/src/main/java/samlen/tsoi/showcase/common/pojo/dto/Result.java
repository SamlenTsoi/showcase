package samlen.tsoi.showcase.common.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author samlen_tsoi
 * @date 2018/1/19
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg(), (Object)null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> ok(Code code, T data) {
        return new Result(code.getCode(), code.getMsg(), data);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result(CommonCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> fail() {
        return fail((Code)CommonCode.FAIL);
    }

    public static <T> Result<T> fail(String error) {
        return new Result(CommonCode.FAIL.getCode(), error, (Object)null);
    }

    public static <T> Result<T> fail(int code, String error) {
        return new Result(Integer.valueOf(code), error, (Object)null);
    }

    public static <T> Result<T> fail(Code code) {
        return new Result(code.getCode(), code.getMsg(), (Object)null);
    }

    public static <T> Result<T> fail(Code code, String error) {
        return new Result(code.getCode(), error, (Object)null);
    }
}
