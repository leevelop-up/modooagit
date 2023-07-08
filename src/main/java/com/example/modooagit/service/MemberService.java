package com.example.modooagit.service;

import com.example.modooagit.domain.Member;
import com.example.modooagit.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {

    private  MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 값이 있습니다");
                });
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> fineOne(Long memberId){
        return memberRepository.findById(memberId);
    }


    public  Member authenticate(String name, String pw) {
        Member member = memberRepository.findByName(name).orElse(null);
        return null;
    }
}
