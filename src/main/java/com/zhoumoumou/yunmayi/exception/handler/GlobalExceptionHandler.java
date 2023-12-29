package com.zhoumoumou.yunmayi.exception.handler;

import com.zhoumoumou.yunmayi.exception.GlobalException;
import com.zhoumoumou.yunmayi.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 处理自定义异常
     *
     * @return 处理结果
     * @Param e 异常
     */

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GlobalException.class)
    public R handlerGlobalException(GlobalException e) {
        logger.error(e.getMessage(), e);
        return R.build(e.getCode(), e.getMessage());

    }

    /**
     * 全局捕获security的权限不足异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public R exceptionHandler(AccessDeniedException e) throws AccessDeniedException {
        logger.error(e.getMessage(), e.getCause());
        throw e;
    }

//    /**
//     * 全局捕获security的认证失败异常
//     */
//    @ExceptionHandler(value = AuthenticationException.class)
//    public R exceptionHandler(AuthenticationException e) throws AuthenticationException {
//        logger.error(e.getMessage(), e);
//        throw e;
//    }

    @ExceptionHandler(BindException.class)
    public R handlerBindException(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        logger.error(e.getMessage());
        return R.fail().message(objectError.getDefaultMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handlerMissingServletRequestParameterException(MissingServletRequestParameterException e){
        return R.fail().message("缺少参数："+e.getParameterName());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error(e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        return R.fail().message(allErrors.get(0).getDefaultMessage());
    }



    @ExceptionHandler(value = ConstraintViolationException.class)
    public R exception(ConstraintViolationException e) {
        logger.error(e.getMessage());
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String collect = constraintViolations.stream().map(one -> one.getMessage()).collect(Collectors.joining(";"));
        return R.fail().message(collect);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public R handlerUnexpectedTypeException(UnexpectedTypeException e) {
        String message = e.getMessage();
        logger.error(e.getMessage(), e);
        return R.fail().message("参数校验失败: " + message);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public R handlerBatchUpdateException(DuplicateKeyException e){
        String message = e.getMessage();
        Pattern compile = Pattern.compile("'([^']+)' ");
        Matcher matcher = compile.matcher(message);
        ArrayList<String> split = new ArrayList<>();
        while (matcher.find()){
            split.add(matcher.group(1));
        }
        if (message.contains("Duplicate entry")){
           return R.fail(split.get(0)+"已存在");
        }
        return R.fail(message);
    }
    /**
     * 处理 Exception 异常
     *
     * @param e 异常
     * @return 处理结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e) {
        logger.error(e.getMessage());
        return R.fail().message("统一异常处理");
    }
}
