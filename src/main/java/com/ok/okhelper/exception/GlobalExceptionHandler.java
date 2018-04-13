package com.ok.okhelper.exception;


import com.ok.okhelper.common.ServerResponse;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zc on 2018/4/10.
 */

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

//    private static final String ERROR_PATH = "/error";

    //参数错误 400(bean 校验)
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalExceptionBean(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder("参数错误:");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append(fieldError.getDefaultMessage()).append(";");
        }

        return ServerResponse.createByErrorCodeMessage(HttpStatus.BAD_REQUEST.value(), errorMesssage.toString());
    }

    //参数错误 400
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalException(ServletRequestBindingException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    //参数错误 400 手动
    @ExceptionHandler(IllegalException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalException(IllegalException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    // 未登陆 401
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Object handUnauthorizedException(UnauthenticatedException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    // 权限不足 403
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Object handIllegalException(UnauthorizedException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }


    // 未找到资源 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handNotFoundException(NotFoundException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }


    //请求方法不对 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Object handServletIllegalException(HttpRequestMethodNotSupportedException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }


    //资源已存在 409
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Object handIllegalException(ConflictException e) {
        return ServerResponse.createByErrorCodeMessage(HttpStatus.CONFLICT.value(), e.getMessage());
    }


    //默认异常 500(默认)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultErrorHandler(Exception e) {
        //打日志。。。。
        return ServerResponse.createDefaultErrorMessage(e.getMessage());
    }


    //重写404错误
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public Object handleError() {
//        return ServerResponse.createByErrorCodeMessage(HttpStatus.NOT_FOUND.value(), "Request resource not found1");
//    }


//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
}
