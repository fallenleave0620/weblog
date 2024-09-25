package com.quanxiaoha.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

//注意，需继承自 AuthenticationException，只有该类型异常，才能被后续自定义的认证失败处理器捕获到。
public class UsernameOrPasswordNullException extends AuthenticationException {
    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}
