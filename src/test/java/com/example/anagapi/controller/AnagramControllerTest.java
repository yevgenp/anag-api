package com.example.anagapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AnagramControllerTest {
    private static final String BASE_URL = AnagramController.URL + "/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetTrueIsAnagramResponse() throws Exception {
        //given
        String str1 = "debit card";
        String str2 = "bad credit";
        String url = BASE_URL + str1 + "/" + str2;
        String expected = "{areAnagrams: true}";
        //when
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void shouldGetFalseIsAnagramResponse() throws Exception {
        //given
        String str1 = "qwe";
        String str2 = "qee";
        String url = BASE_URL + str1 + "/" + str2;
        String expected = "{areAnagrams: false}";
        //when
        mockMvc.perform(get(url))
                .andExpect(status().is(200))
                .andExpect(content().json(expected));
    }

    @Test
    void shouldGetBadRequestForIsAnagramResponse() throws Exception {
        //given
        String str1 = "str1";
        String str2 = "str$";
        String url = BASE_URL + str1 + "/" + str2;
        //when
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetAllAnagrams() throws Exception {
        //given
        String str = "skate";
        String url = BASE_URL + str;
        String expected = "{anagrams: [stake, steak, takes]}";
        //when
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void shouldGetBadRequestForGetAllAnagrams() throws Exception {
        //given
        String str = "!str";
        String url = BASE_URL + str;
        //when
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest());
    }

}