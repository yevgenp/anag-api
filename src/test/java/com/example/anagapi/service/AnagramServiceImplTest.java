package com.example.anagapi.service;

import com.example.anagapi.exception.IllegalAnagramArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnagramServiceImplTest {
    @Spy
    private AnagramServiceImpl service;

    @Test
    void shouldValidateArguments() {
        assertThrows(IllegalAnagramArgumentException.class, () -> service.isAnagram(null, ""));
        assertThrows(IllegalAnagramArgumentException.class, () -> service.isAnagram("af", null));
        assertThrows(IllegalAnagramArgumentException.class, () -> service.isAnagram(null, null));
        assertThrows(IllegalAnagramArgumentException.class, () -> service.isAnagram("qw#", "str"));
    }

    @ParameterizedTest
    @CsvSource({"cinema,iceman", "cInema,iceman", "debit card,bad credit", "a123,3a21",
            "School master,The classroom", "Eleven plus two,Twelve plus one"})
    void shouldReturnTrue(String str1, String str2) {
        assertTrue(service.isAnagram(str1, str2));
    }

    @ParameterizedTest
    @CsvSource({"we,dfe", "dsf 1,sd", "dd,aa", "abc def,abcdef"})
    void shouldReturnFalse(String str1, String str2) {
        assertFalse(service.isAnagram(str1, str2));
    }

    @Test
    void shouldGetAllAnagrams() {
        assertThat(service.getAllAnagrams("astt")).isEmpty();
        assertThat(service.getAllAnagrams("cinema")).containsExactlyInAnyOrder("anemic", "iceman");
        assertThat(service.getAllAnagrams("stop")).containsExactlyInAnyOrder("post", "pots", "spot", "tops");
    }
}