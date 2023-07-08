package com.example.modooagit.controller;

import com.example.modooagit.service.MemberService;
import com.example.modooagit.service.NameNotExistedException;
import com.example.modooagit.service.PasswordWrongException;
import com.example.modooagit.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private JwtUtil jwtUtil;


    @MockBean
    private MemberService memberService;

    @Test
    public void createWithInvalidAtributes() throws Exception{
        given(memberService.authenticate("asdf","asdf"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"asdf\",\"pw\": \"asdf\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
               .andExpect(content().string(containsString("{\"accessToken\":\"")));


        verify(memberService).authenticate(eq("asdf"),eq("asdf"));
    }


    @Test
    public void createWithWrongName() throws Exception{
        given(memberService.authenticate("asdf","test"))
                .willThrow(NameNotExistedException.class);

        mvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"asdf\",\"pw\": \"test\"}"))
                .andExpect(status().isBadRequest());

//                .andExpect(header().string("location","/session"))
//                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKEN\"}"));


        verify(memberService).authenticate(eq("asdf"),eq("test"));
    }
    @Test
    public void createWithWrongPassword() throws Exception{
        given(memberService.authenticate("asdf","test"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"asdf\",\"pw\": \"test\"}"))
                .andExpect(status().isBadRequest());

//                .andExpect(header().string("location","/session"))
//                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKEN\"}"));


        verify(memberService).authenticate(eq("asdf"),eq("test"));
    }



}