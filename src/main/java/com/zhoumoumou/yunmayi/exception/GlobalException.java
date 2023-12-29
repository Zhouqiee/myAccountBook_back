package com.zhoumoumou.yunmayi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/18
 */
@Data
public class GlobalException extends RuntimeException{

    /**
     * 保存异常信息
     */
    private String message;

    /**
     * 保存响应状态码
     */
    private Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();


    /**
     * 默认构造方法，根据异常信息 构建一个异常实例对象
     *
     * @param message 异常信息
     */
    public GlobalException(String message) {
        this.message = message;
    }
//    /**
//     * 传入自定义枚举
//     *
//     * @param codeEnum 异常信息
//     */
//    public DyException(CdHrExceptionCodeEnum codeEnum) {
//        super(codeEnum.getMsg());
//        this.code = codeEnum.getCode();
//        this.message = codeEnum.getMsg();
//    }
//
//    /**
//     * 传入自定义枚举
//     *
//     * @param codeEnum 异常信息
//     */
//    public DyException(CdHrExceptionCodeEnum codeEnum, String exception) {
//        super(codeEnum.getMsg());
//        this.code = codeEnum.getCode();
//        this.message = codeEnum.getMsg() + "，非法信息：" + exception;
//    }


    /**
     * 根据异常信息、响应状态码构建 一个异常实例对象
     *
     * @param message 异常信息
     * @param code    响应状态码
     */

    public GlobalException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 根据异常信息，异常对象构建 一个异常实例对象
     *
     * @param message 异常信息
     * @param e       异常对象
     */
    public GlobalException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }


    /**
     * 根据异常信息，响应状态码，异常对象构建 一个异常实例对象
     *
     * @param message 异常信息
     * @param code    响应状态码
     * @param e       异常对象
     */
    public GlobalException(String message, Integer code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }
}
