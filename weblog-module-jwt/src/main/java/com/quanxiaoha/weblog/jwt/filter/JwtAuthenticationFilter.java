package com.quanxiaoha.weblog.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanxiaoha.weblog.jwt.exception.UsernameOrPasswordNullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
//自定义一个用于认证的过滤器
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    /**
     * 指定用户登录的访问地址
     */
//    /login接口
//此过滤器继承了 AbstractAuthenticationProcessingFilter，用于处理 JWT（JSON Web Token）的用户身份验证过程。
    public JwtAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException, IOException {
//  ObjectMapper 是 Jackson 中最核心的类，它提供了序列化和反序列化 JSON 的功能。你可以使用 ObjectMapper 将 Java 对象转换为 JSON 字符串，或者将 JSON 字符串转换为 Java 对象。
        ObjectMapper mapper = new ObjectMapper();
        // 解析提交的 JSON 数据，处理 JSON 树模型
//  JsonNode 是 Jackson 提供的一个树状结构，用于表示 JSON 文档中的所有可能的结构（对象、数组、值等）。通过 JsonNode，你可以以树状结构的方式遍历和操作 JSON 数据，而不需要立即将其转换为具体的 Java 对象。
        JsonNode jsonNode = mapper.readTree(request.getInputStream());
        JsonNode usernameNode = jsonNode.get("username");
        JsonNode passwordNode =  jsonNode.get("password");

        // 判断用户名、密码是否为空
        if (Objects.isNull(usernameNode) || Objects.isNull(passwordNode)
                || StringUtils.isBlank(usernameNode.textValue()) || StringUtils.isBlank(passwordNode.textValue())) {
            throw new UsernameOrPasswordNullException("用户名或密码不能为空");
        }

        String username = usernameNode.textValue();
        String password = passwordNode.textValue();

        // 将用户名、密码封装到 Token 中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }
}
