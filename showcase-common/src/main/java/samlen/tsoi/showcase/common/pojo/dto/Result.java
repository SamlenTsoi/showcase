package samlen.tsoi.showcase.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samlen_tsoi
 * @date 2018/1/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> ok() {
        return new Result(CommonCode.SUCCESS.getCode(), CommonCode.SUCCESS.getMsg(), null);
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
        return fail(CommonCode.FAIL);
    }

    public static <T> Result<T> fail(String error) {
        return new Result(CommonCode.FAIL.getCode(), error, null);
    }

    public static <T> Result<T> fail(int code, String error) {
        return new Result(Integer.valueOf(code), error, null);
    }

    public static <T> Result<T> fail(Code code) {
        return new Result(code.getCode(), code.getMsg(), null);
    }

    public static <T> Result<T> fail(Code code, String error) {
        return new Result(code.getCode(), error, null);
    }
}
