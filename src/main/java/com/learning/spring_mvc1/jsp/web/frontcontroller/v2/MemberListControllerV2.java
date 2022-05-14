package com.learning.spring_mvc1.jsp.web.frontcontroller.v2;

import com.learning.spring_mvc1.jsp.domain.member.Member;
import com.learning.spring_mvc1.jsp.domain.member.MemberRepository;
import com.learning.spring_mvc1.jsp.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);
        return new MyView("/WEB-INF/views/members.jsp");
    }
}