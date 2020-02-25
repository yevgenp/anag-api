package com.example.anagapi.controller;

import com.example.anagapi.dto.AnagramResponse;
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
    public AnagramResponse isAnagram(@PathVariable String string1, @PathVariable String string2) {
        return new AnagramResponse(anagramService.isAnagram(string1, string2));
    }
}
