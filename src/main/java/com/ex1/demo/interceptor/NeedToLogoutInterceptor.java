package com.ex1.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ex1.demo.dto.Rq;
import com.ex1.demo.util.Util;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NeedToLogoutInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Rq rq = (Rq) req.getAttribute("rq");

        if (rq.isLogined()) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().append(Util.msgAndBack("로그아웃 후 이용해주세요."));
            return false;
        }

        return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}