package com.learning.spring_mvc1.jsp.web.frontcontroller.v3;

import com.learning.spring_mvc1.jsp.web.frontcontroller.ModelView;
import com.learning.spring_mvc1.jsp.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final static String PREFIX = "/WEB-INF/views/";
    private final static String SUFFIX = ".jsp";

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members/", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");


        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMAp = createParamMap(request);
        ModelView modelView = controller.process(paramMAp);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        view.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(PREFIX + viewName + SUFFIX);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMAp = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMAp.put(paramName, request.getParameter(paramName)));
        return paramMAp;
    }
}
