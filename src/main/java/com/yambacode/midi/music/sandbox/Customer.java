package com.yambacode.midi.music.sandbox;


import java.util.function.Predicate;

import static com.yambacode.midi.music.sandbox.Role1.sender;

public class Customer implements Role1, Role2 {


    public void doStuff() {
    }

    public static void main(String[] args) {

        Attribute1 sender = Role1.sender;
        Predicate<String>[] validation = sender.getValidation();

        Attribute2 kat = Customer.kat;
        Attribute1 receiver = Customer.receiver;

    }


}
