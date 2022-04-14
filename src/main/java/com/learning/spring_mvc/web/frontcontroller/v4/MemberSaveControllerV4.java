package com.learning.spring_mvc.web.frontcontroller.v4;

import com.learning.spring_mvc.domain.member.Member;
import com.learning.spring_mvc.domain.member.MemberRepository;
import com.learning.spring_mvc.web.frontcontroller.ModelView;
import com.learning.spring_mvc.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
