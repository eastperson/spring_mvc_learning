package com.learning.spring_mvc1.jsp.web.frontcontroller.v3;

import com.learning.spring_mvc1.jsp.domain.member.Member;
import com.learning.spring_mvc1.jsp.domain.member.MemberRepository;
import com.learning.spring_mvc1.jsp.web.frontcontroller.ModelView;

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
