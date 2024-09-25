package com.quanxiaoha.weblog.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanxiaoha.weblog.common.utils.Response;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
//为了在过滤器中方便的返回 JSON 参数，我们需要封装一个工具类 ResultUtil

public class ResultUtil {

    /**
     * 成功响参
     * @param response
     * @param result
     * @throws IOException
     */
    public static void ok(HttpServletResponse response, Response<?> result) throws IOException, java.io.IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响参
     * @param response
     * @param result
     * @throws IOException
     */
//    在 Java 中，Response<?> 通常是一个泛型类，用于表示一个 HTTP 响应。这个类通常包含有关响应的各种信息，如状态代码、头部信息、主体内容等。这里的问号 ? 是一个通配符，表示 Response 类可以持有任何类型的数据。
    public static void fail(HttpServletResponse response, Response<?> result) throws IOException, java.io.IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
//  在 Java 编程中，PrintWriter 是一个用于向流写入字符数据的便捷类。当你在处理 HTTP 请求和响应时，尤其是在使用 Servlet 技术时
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
//  mapper.writeValueAsString 是 Jackson 库中的一个方法，用于将 Java 对象序列化为 JSON 格式的字符串。
        writer.write(mapper.writeValueAsString(result));
//  在 Java 中，flush() 方法是许多输出流类（如 OutputStream、Writer 及其子类）的一个方法，用于强制将流中缓冲的数据立即写出。对于 PrintWriter 这样的类，flush() 方法确保所有待处理的输出都被发送到目的地，而不是等到缓冲区满了或者流被关闭时才进行。
        writer.flush();
        writer.close();
    }

    /**
     * 失败响参
     * @param response
     * @param status 可指定响应码，如 401 等
     * @param result
     * @throws IOException
     */
    public static void fail(HttpServletResponse response, int status, Response<?> result) throws IOException, java.io.IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
