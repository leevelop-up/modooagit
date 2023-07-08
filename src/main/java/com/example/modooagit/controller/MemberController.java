package com.example.modooagit.controller;

import com.example.modooagit.domain.Member;
import com.example.modooagit.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private  final MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("members/new")
    public String creat(MemberForm form){
        //패스워드 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(form.getPw());
        Member member = new Member();
        member.setName(form.getName());
        member.setPw(encodedPassword);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> member = memberService.findMembers();
        model.addAttribute("members",member);
        return "members/memberList";
    }
    @GetMapping("/members/login")
    public String login(Model model){
        List<Member> member = memberService.findMembers();
        model.addAttribute("members",member);
        return "members/login";
    }

    @PostMapping("members/login")
    public String login(MemberForm form){
        //패스워드 암호화
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(form.getPw());
        Member member = new Member();
        member.setName(form.getName());
        member.setPw(encodedPassword);
        memberService.join(member);
        return "members/memberList";
    }
}
