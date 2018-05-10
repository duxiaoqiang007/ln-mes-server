package com.smu.vaan;

import com.smu.vaan.exception.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by vaan on 2017/2/23.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";



    // 通用异常的处理，返回500
//    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)  // 500
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public ResponseEntity handleException(Exception e) {
//        return ResponseEntity.ok(new MyErrorResponse(500, e.getMessage()));
//    }



    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ResponseEntity handleUnauthorizedException(HttpServletRequest req, BadCredentialsException e) {
//        return new ResponseEntity<Object>(
//                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
        return ResponseEntity.ok(
                new MyErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                        "用户名或密码错误",
                        req.getRequestURI()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity handleUnauthorizedException(HttpServletRequest req, UsernameNotFoundException e) {
        return ResponseEntity.ok(
                new MyErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                        "未找到该用户",
                        req.getRequestURI()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity handleAccessDeniedException(HttpServletRequest req, AccessDeniedException e) {
        return ResponseEntity.ok(new MyErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "操作失败, 权限不足!",
                req.getRequestURI()));
    }

    @ExceptionHandler(value = DisabledException.class)
    @ResponseBody
    public ResponseEntity handleApiExceptionException(HttpServletRequest req, DisabledException e) throws Exception {
        return ResponseEntity.ok(new MyErrorResponse(HttpStatus.UNAUTHORIZED.value(), "该用户尚未激活", req.getRequestURI()));
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ResponseEntity handleApiExceptionException(HttpServletRequest req, ApiException e) throws Exception {
        return ResponseEntity.ok(new MyErrorResponse(e, req.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(HttpServletRequest req, Exception e) {
        return ResponseEntity.ok(new MyErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), req.getRequestURI()));
    }
}