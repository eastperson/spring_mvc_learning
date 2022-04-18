package com.learning.spring_mvc.jsp.web.frontcontroller.v4;

import com.learning.spring_mvc.jsp.web.frontcontroller.ModelView;
import com.learning.spring_mvc.jsp.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private final static String PREFIX = "/WEB-INF/views/";
    private final static String SUFFIX = ".jsp";

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members/", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = new ModelView("index");
        String viewName = controller.process(paramMap, modelView.getModel());

        modelView.setViewName(viewName);
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
