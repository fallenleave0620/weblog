package com.quanxiaoha.weblog.jwt.handler;

import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.jwt.exception.UsernameOrPasswordNullException;
import com.quanxiaoha.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
//在 /handler 包下，创建 RestAuthenticationFailureHandler 认证失败处理器
//Spring Security 中，AuthenticationFailureHandler 和 AuthenticationSuccessHandler 是用于处理身份验证失败和成功的接口。它们允许您在用户身份验证过程中自定义响应，以便更好地控制和定制用户体验。
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            // 用户名或密码为空
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            // 用户名或密码错误
//            编辑 weblog-module-common 模块中的 ResponseCodeEnum 枚举类，添加登录失败的响应码
            ResultUtil.fail(response, Response.fail(ResponseCodeEnum.USERNAME_OR_PWD_ERROR));
        }

        // 登录失败
//        编辑 weblog-module-common 模块中的 ResponseCodeEnum 枚举类，添加登录失败的响应码
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.LOGIN_FAIL));
    }
}
