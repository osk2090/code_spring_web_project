package com.osk2090.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

public class WebConfig extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};//스프링 MVC의 기본 경로 /로 수정
    }

    @Override//디스패쳐서블릿 추가설정을 위한 메서드중 하나
    protected void customizeRegistration(
            ServletRegistration.Dynamic registration
            //애플리케이션의 리로딩없이 프로그램 내부에서 동적으로 웹객체를 추가
            ) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        //CommonExceptionAdvice.class가 동작한다면 즉 에러404가 뜬다면
        //custom404.jsp가 출력되게 한다.
    }
}
