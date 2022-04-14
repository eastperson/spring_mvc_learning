package com.learning.spring_mvc.web.frontcontroller.v3;

import com.learning.spring_mvc.domain.member.Member;
import com.learning.spring_mvc.domain.member.MemberRepository;
import com.learning.spring_mvc.web.frontcontroller.ModelView;
import com.learning.spring_mvc.web.frontcontroller.MyView;
import com.learning.spring_mvc.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        ModelView modelView = new ModelView("/WEB-INF/views/members.jsp");

        List<Member> members = memberRepository.findAll();
        modelView.getModel().put("members", members);
        return modelView;
    }
}
