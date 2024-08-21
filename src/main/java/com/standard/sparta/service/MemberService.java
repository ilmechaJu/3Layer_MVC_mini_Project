package com.standard.sparta.service;

import com.standard.sparta.domain.Member;
import com.standard.sparta.dto.MemberCreateRequestDto;
import com.standard.sparta.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    /**
     * 회원 생성 서비스
     * @param_data 회원 생성 데이터를 담은 DTO 객체
     */
    // entitiy 객체로 변환해서 쓰는 이유 : 고객으로부터 온 '정보'는 항상 믿을만한 정보인지 확인해야하기 때문.
    public void createMember(MemberCreateRequestDto data){
        log.info("::: - MemberService - createMember() :::");
        //엔티티 조합
        Member newMember = new Member(data.getEmail(), data.getName());

        /*엔티티 조합 방법2
        Member newMemeber = new Member();
        newMember.setEmail("email");
        newMemeber.setName("name");*/

        //회원 저장
        Member savedMember = memberRepository.save(newMember);

    }
}
