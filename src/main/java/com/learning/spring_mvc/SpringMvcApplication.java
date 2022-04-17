package com.learning.spring_mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/** 서블릿 컴포넌트 스캔 (서블릿 자동 등록) 하위 패키지의 서블릿을 찾아서 실행할 수 있도록 도와준다. **/
@ServletComponentScan
@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }
}
