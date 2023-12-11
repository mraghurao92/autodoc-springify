package com.mraghu.autodoc.interceptor;

import com.mraghu.autodoc.model.CustomRequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    private CustomRequestContext customRequestContext;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        customRequestContext.setCustomHeader(request.getHeader("customHeader"));
        return true;

    }


}
