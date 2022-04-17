package com.learning.spring_mvc.web.springmvc.v1;

import com.learning.spring_mvc.domain.member.Member;
import com.learning.spring_mvc.domain.member.MemberRepository;
import com.learning.spring_mvc.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/springmvc/v1/members")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }
}
