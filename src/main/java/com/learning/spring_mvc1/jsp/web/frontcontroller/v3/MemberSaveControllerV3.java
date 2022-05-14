package com.learning.spring_mvc1.jsp.web.frontcontroller.v3;

import com.learning.spring_mvc1.jsp.domain.member.Member;
import com.learning.spring_mvc1.jsp.domain.member.MemberRepository;
import com.learning.spring_mvc1.jsp.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        ModelView modelView = new ModelView("save-result");

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        modelView.getModel().put("member", member);

        return modelView;
    }
}
