package com.example.dictionary.model;
import java.util.Objects;

public class Entry {
    String word;
    String definition;


    public Entry() {
    }

    public Entry(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Entry word(String word) {
        setWord(word);
        return this;
    }

    public Entry definition(String definition) {
        setDefinition(definition);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " word='" + getWord() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }

}
