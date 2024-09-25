package com.quanxiaoha.weblog.jwt.filter;

import com.quanxiaoha.weblog.jwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.tokenHeaderKey}")
    private String tokenHeaderKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取 key 为 Authorization 的值
        String header = request.getHeader(tokenHeaderKey);

        // 判断 value 值是否以 Bearer 开头
        if (StringUtils.startsWith(header, tokenPrefix)) {
            // 截取 Token 令牌
            String token = StringUtils.substring(header, 7);
            log.info("Token: {}", token);

            // 判空 Token
            if (StringUtils.isNotBlank(token)) {
                try {
                    // 校验 Token 是否可用, 若解析异常，针对不同异常做出不同的响应参数
                    jwtTokenHelper.validateToken(token);
//                    ExpiredJwtException：token已过期
//SignatureException：签名验证失败
//MalformedJwtException：token格式不正确
                } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
                    // 抛出异常，统一让 AuthenticationEntryPoint 处理响应参数
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (ExpiredJwtException e) {
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    return;
                }

                // 从 Token 中解析出用户名
                String username = jwtTokenHelper.getUsernameByToken(token);

                if (StringUtils.isNotBlank(username)
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    // 根据用户名获取用户详情信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 将用户信息存入 authentication，方便后续校验
//                    `Collection` 是Java中一个非常重要的接口，它是Java集合框架的根接口之一。Object principal/用户名, Object credentials/用户凭证/密码？, Collection<? extends GrantedAuthority限制实现该接口的方法/
//   - 通常用来代表用户的角色或权限。>泛型集合 authorities
//                    collection- 它代表一组对象，这些对象也被称为 Collection 的元素,无顺序/可重复/可为null
//
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    ThreadLocal 是Java中一个非常有用的类，用于创建线程局部变量。让我为您详细解释：
//                    定义：
//                    ThreadLocal 类提供了线程局部变量，这些变量对于每个线程都是独立的。
//                    主要特点：
//                    每个线程都有自己的变量副本，互不干扰
//                            适用于需要在一个线程中跨越多个方法和类的数据共享
//                    避免了参数的显式传递，简化了代码
                }
            }
        }

        // 继续执行写一个过滤器
        filterChain.doFilter(request, response);
    }
}
