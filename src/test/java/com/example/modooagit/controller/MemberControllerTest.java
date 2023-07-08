package com.example.modooagit.controller;

import com.example.modooagit.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;

import static org.junit.jupiter.api.Assertions.*;


class MemberControllerTest {
    @Test
    @PostMapping("members/new")
    public String creat(MemberForm form){
        Member member = new Member();
       String eifn = "asdf";

        member.setName(eifn);
        member.setPw(form.getPw());

        return "redirect:/";
    }
}