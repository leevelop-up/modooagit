package com.example.modooagit.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardControllerTest {
    @Autowired
    //bean을 주입하겠다.
    private BoardController boardController;
    private MockMvc mockMvc;
    @Test
    void helloworld(){
//        System.out.println("test");
        //System.out.println(boardController.index());
        assertThat(boardController.index().equals("Gree1tings from Spring Boot"));
    }
    @Test
    void MockMvcTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloWorld")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Greetings from Spring Boot!")); //BoardController의 값과 비교
    }
}