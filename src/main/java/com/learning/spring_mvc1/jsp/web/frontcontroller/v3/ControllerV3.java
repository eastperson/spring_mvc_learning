package com.learning.spring_mvc1.jsp.web.frontcontroller.v3;

import com.learning.spring_mvc1.jsp.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
