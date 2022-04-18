package com.learning.spring_mvc.jsp.web.frontcontroller.v3;

import com.learning.spring_mvc.jsp.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}