package com.example.dictionary.service;

import org.springframework.stereotype.Service;

import com.example.dictionary.exception.WordNotFoundException;
import com.example.dictionary.model.Entry;
import com.example.dictionary.reference.DictionaryReference;

@Service
public class DictionaryService {

    public Entry getWord(String word) throws WordNotFoundException {
        Entry entry = new Entry(word, DictionaryReference.getDictionary().get(word));
        return entry;
    }
}
