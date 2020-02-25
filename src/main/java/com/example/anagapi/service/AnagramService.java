package com.example.anagapi.service;

import java.util.Set;

public interface AnagramService {
    boolean isAnagram(String str1, String str2);

    Set<String> getAllAnagrams(String string);
}
