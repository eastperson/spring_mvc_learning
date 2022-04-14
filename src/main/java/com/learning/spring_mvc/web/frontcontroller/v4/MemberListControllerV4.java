package com.learning.spring_mvc.web.frontcontroller.v4;

import com.learning.spring_mvc.domain.member.Member;
import com.learning.spring_mvc.domain.member.MemberRepository;
import com.learning.spring_mvc.web.frontcontroller.ModelView;
import com.learning.spring_mvc.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
