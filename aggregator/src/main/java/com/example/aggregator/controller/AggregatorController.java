package com.example.aggregator.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AggregatorController {
    private static final Logger logger = LoggerFactory.getLogger(AggregatorController.class);
    private final AggregatorService service;


    public AggregatorController(AggregatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Entry> helloWorld() {
        List<Entry> entries = new ArrayList<>();
        entries.add(service.getDefinitionFor("hello"));
        entries.add(service.getDefinitionFor("world"));
        return entries;
    }

    @GetMapping("/getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {
        return service.getDefinitionFor(word);
    }

    @GetMapping("/getWordsThatContainSuccessiveLettersAndStartsWith/{chars}")
    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(@PathVariable String chars) {
        return service.getWordsThatContainSuccessiveLettersAndStartsWith(chars);
    }


}
