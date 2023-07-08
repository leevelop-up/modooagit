package com.example.modooagit.controller;

import com.example.modooagit.service.MemberService;
import com.example.modooagit.service.SessionRequestDto;
import com.example.modooagit.service.SessionResponseDto;
import com.example.modooagit.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {
    @Autowired
    private MemberService memberService;



    @Autowired
    private JwtUtil jwtUtil;




    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource
    ) throws URISyntaxException {
        String url = "/session";
        String accessToken = "ACCESSTOKEN";
        String name = resource.getName();
        String pw = resource.getPw();
        memberService.authenticate(name,pw);
        SessionResponseDto sessionDto =  SessionResponseDto.builder().accessToken(accessToken).build();
        return ResponseEntity.created(new URI(url)).body(sessionDto);
    }
}
