package com.sbrf.reboot.atm.cassettes;

public class Generate {
    public static <T extends Comparable<T>, V extends T> boolean isInArray(T x, V[] array){
        for (V v : array) {
            if (x.equals(v)) return true;
        }
        return false;
    }
}
