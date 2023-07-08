package com.example.modooagit.service;

import com.example.modooagit.domain.Member;
import com.example.modooagit.repository.MemberRepository;
import com.example.modooagit.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.modooagit.domain.Member.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.DoubleStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach

    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();

        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.fineOne(saveId).get();
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 중복_회원_예외(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 값이 있습니다");

        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 값이 있습니다123");
        }
*/
    }


    @Test
    public void authenticateWithValidAttributes() {
        String name = "tester@example.com";
        String password = "test";


        Member mockUser = Member.builder()
                .name(name).build();
        given(memberRepository.findByName(name))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        Member user = memberService.authenticate(name, password);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void authenticateWithNotExistedEmail() {
        String email = "x@example.com";
        String password = "test";

        given(memberRepository.findByName(email))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            memberService.authenticate(email, password);
        }).isInstanceOf(NameNotExistedException.class);
    }

    @Test
    public void authenticateWithWrongPassword() {
        String email = "tester@example.com";
        String password = "x";

        Member mockUser = Member.builder().pw(password).build();

        given(memberRepository.findByName(email))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThatThrownBy(() -> {
            memberService.authenticate(email, password);
        }).isInstanceOf(PasswordWrongException.class);
    }
}