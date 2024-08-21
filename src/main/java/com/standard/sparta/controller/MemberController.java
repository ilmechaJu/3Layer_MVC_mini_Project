package com.standard.sparta.controller;

import com.standard.sparta.dto.MemberCreateRequestDto;
import com.standard.sparta.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
    //1.속성
    @Autowired
    private MemberService memberService;
    //2.생성자

    //3. 메소드
    /**
     * 1. 회원 생성을 위한 뷰를 반환합니다.
     * @return view 이름 반환
     */
    @GetMapping("/create-form")
    public String createMemberView(){
        log.info("::: MemberController - createMemberView() :::");
        return "member-create-form";
    }

    @PostMapping() //홈페이지에서 사용자가 쓴 글자를 받아오는건 Dto로 받아야함
    public String createMemberAPI(@ModelAttribute MemberCreateRequestDto request){
        log.info("::: MemberController - createMemberAPI() :::");

        memberService.createMember(request);
        return "member-create-success";
    }
}


