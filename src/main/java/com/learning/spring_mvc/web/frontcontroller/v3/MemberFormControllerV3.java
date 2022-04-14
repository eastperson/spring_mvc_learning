package com.learning.spring_mvc.web.frontcontroller.v3;

import com.learning.spring_mvc.web.frontcontroller.ModelView;
import com.learning.spring_mvc.web.frontcontroller.MyView;
import com.learning.spring_mvc.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}