package com.example.aggregator.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;

@Service
public class AggregatorService {

    private final AggregatorRestClient restClient;

    public AggregatorService(AggregatorRestClient restClient) {
        this.restClient = restClient;
    }

    public Entry getDefinitionFor(String word) {
        return restClient.getDefinitionFor(word);
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(String chars) {
        List<Entry> wordsThatStartWith = restClient.getWordsStartingWith(chars);
        List<Entry> wordsThatContainSuccessiveLetters = restClient.getWordsThatContainConsecutiveLetters();

        List<Entry> common = new ArrayList<>(wordsThatStartWith);
        common.retainAll(wordsThatContainSuccessiveLetters);

        return common;
    }

    public List<Entry> getAllPalindromes() {

        final List<Entry> candidates = new ArrayList<>();

        // Iterate from a to z
        IntStream.range('a', '{')
            .mapToObj(i -> Character.toString(i))
            .forEach(c -> {

            // get words starting and ending with character
            List<Entry> startsWith = restClient.getWordsStartingWith(c);
            List<Entry> endsWith = restClient.getWordsEndingWith(c);

            // keep entries that exist in both lists
            List<Entry> startsAndEndsWith = new ArrayList<>(startsWith);
            startsAndEndsWith.retainAll(endsWith);

            // store list with existing entries
            candidates.addAll(startsAndEndsWith);

            });

        // test each entry for palindrome, sort and return
        return candidates.stream()
            .filter(entry -> {
                String word = entry.getWord();
                String reverse = new StringBuilder(word).reverse()
                                                        .toString();
                return word.equals(reverse);
            })
            .sorted()
            .collect(Collectors.toList());
    }

    public List<Entry> getAllPalindromesV2() {

        final List<Entry> palindromes = new ArrayList<>();

        // Iterate from a to z
        for(char character = 'a'; character <= 'z'; character++) {

            // Change the char to string for end point
            String letter = Character.toString(character);

            // Get all entries starting with the letter
            List<Entry> entires = restClient.getWordsStartingWith(letter);

            // Iterate over the entries
            for(Entry entry: entires) {

                // Check the letters of the word starting with the first and last index positions
                String word = entry.getWord();
                int startIndex = 0;
                int endIndex = word.length() - 1;

                // Loop until the start and end position either match or pass each other.
                // If the characters at the startIndex and endIndex position match, keep going.
                while(startIndex < endIndex) {
                    if (word.charAt(startIndex) == word.charAt(endIndex)) {
                        startIndex++;
                        endIndex--;
                    }
                    else {
                        break;
                    }
                }

                // If the start index is >= the end index, then we went through the whole word without
                // breaking. This would be a palindrome. Add it to the array.
                if (startIndex >= endIndex) {
                    palindromes.add(entry);
                }
            }
        }

        // sort before returning using the natural order to compare.
        palindromes.sort(Comparator.naturalOrder());
        return palindromes;
    }

}
