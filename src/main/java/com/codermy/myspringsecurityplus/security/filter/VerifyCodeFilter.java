package com.codermy.myspringsecurityplus.security.filter;

import com.alibaba.fastjson.JSON;
import com.codermy.myspringsecurityplus.utils.Result;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author codermy
 * @createTime 2020/7/20
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {

    private String defaultFilterProcessUrl = "/login";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 登录请求校验验证码，非登录请求不用校验
            HttpSession session = request.getSession();
            String requestCaptcha = request.getParameter("captcha");
            String genCaptcha = (String) request.getSession().getAttribute("captcha");//验证码的信息存放在seesion种，具体看EasyCaptcha官方解释
            response.setContentType("application/json;charset=UTF-8");
            if (StringUtils.isEmpty(requestCaptcha)){
                session.removeAttribute("captcha");//删除缓存里的验证码信息
                response.getWriter().write(JSON.toJSONString(Result.error().message("验证码不能为空!")));
                throw new AuthenticationServiceException("验证码不能为空!");
            }
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                session.removeAttribute("captcha");
                response.getWriter().write(JSON.toJSONString(Result.error().message("验证码错误!")));
                throw new AuthenticationServiceException("验证码错误!");
            }
        }
        chain.doFilter(request, response);
    }
}
