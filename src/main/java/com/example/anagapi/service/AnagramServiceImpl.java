package com.example.anagapi.service;

import com.example.anagapi.exception.IllegalAnagramArgumentException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class AnagramServiceImpl implements AnagramService {
    public boolean isAnagram(String str1, String str2) {
        validate(str1, str2);
        if (str1.length() != str2.length())
            return false;
        Map<Character, Integer> map1 = str1.toLowerCase().chars().mapToObj(c -> (char) c).collect(toMap(k -> k, v -> 1, Integer::sum));
        Map<Character, Integer> map2 = str2.toLowerCase().chars().mapToObj(c -> (char) c).collect(toMap(k -> k, v -> 1, Integer::sum));
        return map1.equals(map2);
    }

    void validate(String str1, String str2) {
        String valid = "[\\p{Alnum}\\p{Space}]*";
        if (str1 == null || str2 == null)
            throw new IllegalAnagramArgumentException("Nulls are not allowed");
        if (!str1.matches(valid) || !str2.matches(valid))
            throw new IllegalAnagramArgumentException("Invalid string");
    }
}
