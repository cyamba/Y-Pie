package com.yambacode.midi.music.sandbox;

import java.util.function.Predicate;

public enum Attribute1 {

    Sender(String::isEmpty, s -> s.matches("w+-+\\s+[0-9]]"), String::isBlank),

    Receiver(s ->s.length() < 10);

    private Predicate<String>[] validations;


    public Predicate<String>[] getValidation() {
        return validations;
    }

    Attribute1(Predicate<String>... predicates) {
        this.validations = predicates;
    }
}
