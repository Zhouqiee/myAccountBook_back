package com.zhoumoumou.yunmayi.utils;

import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
public class R<T> {


    private Integer code;

    private String message;

    private T data;

    public R() {
    }

    protected static <T> R<T> build(T data) {
        R<T> result = new R<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> R<T> build(T body, ResultCodeEnum resultCodeEnum) {
        R<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> R<T> build(Integer code, String message) {
        R<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> R<T> ok() {
        return R.ok(null);
    }

    /**
     * 操作成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data) {
        R<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> R<T> fail() {
        return R.fail(null);
    }

    /**
     * 操作失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(T data) {
        R<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public R<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if (this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
