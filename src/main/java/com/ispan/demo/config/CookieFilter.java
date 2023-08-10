package com.ispan.demo.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieFilter implements Filter {
	  @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        // 初始化方法，可在启动时执行一些初始化操作
	    }


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    String userName = cookie.getValue();
                    // 根据 userName 获取用户信息或进行其他处理
                    // ...
                }
            }
        }
        
        // 继续处理请求链
        chain.doFilter(request, response);

	}
	
    @Override
    public void destroy() {
        // 销毁方法，可在关闭时执行一些清理操作
    }

}
