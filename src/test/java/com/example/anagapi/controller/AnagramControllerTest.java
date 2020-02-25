package com.example.anagapi.controller;

import com.example.anagapi.service.AnagramService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
    @SpyBean
    private AnagramService anagramService;

    @Test
    void shouldGetTrueResponse() throws Exception {
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
    void shouldGetFalseResponse() throws Exception {
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
    void shouldGetBadRequestResponse() throws Exception {
        //given
        String str1 = "str1";
        String str2 = "str$";
        String url = BASE_URL + str1 + "/" + str2;
        //when
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest());
    }
}