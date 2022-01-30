package com.sbrf.reboot.atm.cassettes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CassetteTest {

    class OneHundred extends Banknote {
    }

    class OneThousand extends Banknote {
    }

    @Test
    void getCountBanknotes() {
        OneHundred oneHundred = new OneHundred();

        Cassette<OneHundred> cassette = new Cassette<>(new ArrayList<OneHundred>() {{
            add(oneHundred);
//            add(new OneThousand()); //it will not compile
//            add(new Banknote()); //it will not compile
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }
    @Test
    void successGenerateNames() {
        String[] profileNames = {"Vadim", "Alisa", "Maria", "Evgenia"};
        Assertions.assertTrue(Generate.isInArray("Alisa", profileNames));
    }

    @Test
    void failGenerateNames() {
        String[] profileNames = {"Vadim", "Alise"};
        Assertions.assertFalse(Generate.isInArray("Maria", profileNames));
    }

    @Test
    void successGenerateValuesInCassette() {
        Integer[] money = {10, 20, 30};   // Compare потребует Integer
        Assertions.assertTrue(Generate.isInArray(20, money));
    }

    @Test
    void failGenerateValuesInCassette() {
        Double[] money = {10.0, 20.0, 30.0};  // Compare потребует Double
        Assertions.assertTrue(Generate.isInArray(10.0, money));
    }
}