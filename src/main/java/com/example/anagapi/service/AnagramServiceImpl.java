package com.example.anagapi.service;

import com.example.anagapi.exception.IllegalAnagramArgumentException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.example.anagapi.service.AnagramUtil.buildTrie;
import static java.util.stream.Collectors.toMap;

@Service
public class AnagramServiceImpl implements AnagramService {
    private AnagramUtil.TrieNode dictionary = buildTrie();

    public boolean isAnagram(String str1, String str2) {
        validate(str1, str2);
        if (str1.length() != str2.length())
            return false;
        Map<Character, Integer> map1 = str1.toLowerCase().chars().mapToObj(c -> (char) c).collect(toMap(k -> k, v -> 1, Integer::sum));
        Map<Character, Integer> map2 = str2.toLowerCase().chars().mapToObj(c -> (char) c).collect(toMap(k -> k, v -> 1, Integer::sum));
        return map1.equals(map2);
    }

    private void validate(String... args) {
        String valid = "[\\p{Alnum}\\p{Space}]*";
        for (String str : args) {
            if (str == null || !str.matches(valid))
                throw new IllegalAnagramArgumentException("Invalid string");
        }
    }

    @Override
    public Set<String> getAllAnagrams(String arg) {
        validate(arg);
        Map<Character, Integer> freq = arg.toLowerCase().chars().mapToObj(c -> (char) c).collect(toMap(k -> k, v -> 1, Integer::sum));
        Set<String> anagrams = new HashSet<>();
        AnagramUtil.findAnagrams(dictionary, "", freq, anagrams);
        anagrams.remove(arg);
        return anagrams;
    }

}
