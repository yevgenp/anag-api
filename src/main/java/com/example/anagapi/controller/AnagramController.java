package com.example.anagapi.controller;

import com.example.anagapi.dto.AnagramsResponse;
import com.example.anagapi.dto.IsAnagramResponse;
import com.example.anagapi.service.AnagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = AnagramController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class AnagramController {
    public static final String URL = "/anagrams";

    private final AnagramService anagramService;

    @GetMapping("{string1}/{string2}")
    public IsAnagramResponse isAnagram(@PathVariable String string1, @PathVariable String string2) {
        return new IsAnagramResponse(anagramService.isAnagram(string1, string2));
    }

    @GetMapping("{string}")
    public AnagramsResponse getAllAnagrams(@PathVariable String string) {
        return new AnagramsResponse(anagramService.getAllAnagrams(string));
    }
}
