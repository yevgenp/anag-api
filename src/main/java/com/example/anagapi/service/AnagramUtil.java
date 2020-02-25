package com.example.anagapi.service;

import lombok.Data;

import java.util.*;

import static java.util.Arrays.asList;

class AnagramUtil {
    @Data
    static class TrieNode {
        private final Map<Character, TrieNode> chars = new HashMap<>();
        private boolean isLeaf = false;
    }

    static TrieNode buildTrie() {
        //TODO: Implement dictionary parsing from external resource
        Set<String> dict = Collections.unmodifiableSet(new HashSet<>(asList(
                "anemic", "cinema", "iceman",
                "skate", "stake", "steak", "takes",
                "post", "pots", "spot", "stop", "tops"
        )));
        TrieNode head = new TrieNode();
        for (String str : dict) {
            insert(head, str);
        }
        return head;
    }

    private static void insert(TrieNode head, String str) {
        TrieNode curr = head;
        for (int i = 0; i < str.length(); i++) {
            curr = curr.getChars().computeIfAbsent(str.charAt(i), k -> new TrieNode());
        }
        curr.setLeaf(true);
    }

    static void findAnagrams(TrieNode head, String str, Map<Character, Integer> freq, Set<String> set) {
        if (head == null)
            return;
        if (freq.isEmpty()) {
            if (head.isLeaf())
                set.add(str);
            return;
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            TrieNode curr;
            if ((curr = head.getChars().get(entry.getKey())) != null) {
                Map<Character, Integer> updFreq = new HashMap<>(freq);
                if (entry.getValue() > 1)
                    updFreq.put(entry.getKey(), entry.getValue() - 1);
                else
                    updFreq.remove(entry.getKey());
                findAnagrams(curr, str + entry.getKey(), updFreq, set);
            }
        }
    }
}
