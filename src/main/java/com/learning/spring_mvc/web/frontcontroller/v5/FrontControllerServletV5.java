package com.learning.spring_mvc.web.frontcontroller.v5;

import com.learning.spring_mvc.web.frontcontroller.ModelView;
import com.learning.spring_mvc.web.frontcontroller.MyView;
import com.learning.spring_mvc.web.frontcontroller.v3.MemberFormControllerV3;
import com.learning.spring_mvc.web.frontcontroller.v3.MemberListControllerV3;
import com.learning.spring_mvc.web.frontcontroller.v3.MemberSaveControllerV3;
import com.learning.spring_mvc.web.frontcontroller.v4.MemberFormControllerV4;
import com.learning.spring_mvc.web.frontcontroller.v4.MemberListControllerV4;
import com.learning.spring_mvc.web.frontcontroller.v4.MemberSaveControllerV4;
import com.learning.spring_mvc.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.learning.spring_mvc.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    private final static String PREFIX = "/WEB-INF/views/";
    private final static String SUFFIX = ".jsp";

    /**
     * 생성자는 핸들러 매핑과 어댑터를 초기화(등록)한다.
     * 매핑 정보
     * private final Map<String, Object> handlerMappingMap = new HashMap<>();
     * 매핑 정보의 값이 ControllerV3 , ControllerV4 같은 인터페이스에서 아무 값이나 받을 수 있는
     * Object 로 변경되었다.
     */
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView modelView = adapter.handle(request, response, handler);

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        view.render(modelView.getModel(), request, response);
    }

    /**
     * handler 를 처리할 수 있는 어댑터를 adapter.supports(handler) 를 통해서 찾는다.
     * handler가 ControllerV3 인터페이스를 구현했다면, ControllerV3HandlerAdapter 객체가
     * 반환된다.
     * @param handler
     * @return
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter not exists");
    }

    /**
     * 핸들러 매핑 정보인 handlerMappingMap 에서
     * URL에 매핑된 핸들러(컨트롤러) 객체를 찾아서 반환한다
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView(PREFIX + viewName + SUFFIX);
    }
}
