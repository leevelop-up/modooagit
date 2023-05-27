package com.example.modooagit;

import com.example.modooagit.domain.Member;
import com.example.modooagit.repository.MemberRepository;
import com.example.modooagit.repository.MemoryMemberRepository;
import com.example.modooagit.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
