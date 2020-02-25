package com.example.anagapi.dto;

import lombok.Value;

import java.util.Set;

@Value
public class AnagramsResponse {
    private final Set<String> anagrams;
}
