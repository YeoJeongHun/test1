package com.ex1.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ex1.demo.interceptor.BeforeActionInterceptor;
import com.ex1.demo.interceptor.NeedToLoginInterceptor;
import com.ex1.demo.interceptor.NeedToLogoutInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // beforeActionInterceptor 인터셉터 불러오기
    @Autowired
    BeforeActionInterceptor beforeActionInterceptor;
    
    @Autowired
    NeedToLoginInterceptor needToLoginInterceptor;

    @Autowired
    NeedToLogoutInterceptor needToLogoutInterceptor;


    // 이 함수는 인터셉터를 적용하는 역할을 합니다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
        registry.addInterceptor(beforeActionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resource/**")
                .excludePathPatterns("/error");
        
        registry.addInterceptor(needToLoginInterceptor)
                .addPathPatterns("/mpaUsr/article/write")
                .addPathPatterns("/mpaUsr/article/doWrite")
                .addPathPatterns("/mpaUsr/article/doDelete")
                .addPathPatterns("/mpaUsr/article/modify")
                .addPathPatterns("/mpaUsr/article/doModify")
                .addPathPatterns("/mpaUsr/article/articleLikeAjax")
                .addPathPatterns("/mpaUsr/article/revise")
                .addPathPatterns("/mpaUsr/article/doRevise")
                .addPathPatterns("/mpaUsr/reply/doWrite")
                .addPathPatterns("/mpaUsr/reply/doDelete")
                .addPathPatterns("/mpaUsr/reply/modify")
                .addPathPatterns("/mpaUsr/reply/doModify")
                .addPathPatterns("/mpaUsr/reply/doDeleteAjax")
                .addPathPatterns("/mpaUsr/reply/likeCheckAjax")
                .addPathPatterns("/mpaUsr/member/modify")
                .addPathPatterns("/mpaUsr/member/withdrawal")
                .addPathPatterns("/mpaUsr/member/doModify")
                .addPathPatterns("/mpaUsr/member/checkPassword")
                .addPathPatterns("/mpaUsr/member/doCheckPassword");

        registry.addInterceptor(needToLogoutInterceptor)
        		.addPathPatterns("/mpaUsr/member/findLoginId")
        		.addPathPatterns("/mpaUsr/member/doFindLoginId")
        		.addPathPatterns("/mpaUsr/member/findLoginPw")
        		.addPathPatterns("/mpaUsr/member/doFindLoginPw")
                .addPathPatterns("/mpaUsr/member/login")
                .addPathPatterns("/mpaUsr/member/doLogin")
                .addPathPatterns("/mpaUsr/member/join")
                .addPathPatterns("/mpaUsr/member/doJoin")
                .addPathPatterns("/mpaUsr/member/findLoginId")
                .addPathPatterns("/mpaUsr/member/doFindLoginId")
                .addPathPatterns("/mpaUsr/member/findLoginPw")
                .addPathPatterns("/mpaUsr/member/doFindLoginPw");
    }
    
    //테스트용
    @Value("${custom.filePassUri}")
    private String filePassUri;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:///" + filePassUri + "/")
                .setCachePeriod(20);
    }
}