package com.example;

import java.util.List;

public class Lion  {

    boolean hasMane;
    private Predator feline;

    public Lion(String sex, Feline feline) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }
        this.feline = feline;
    }


    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }


    public List<String> eatMeat() throws Exception {
        return feline.eatMeat();
    }
}
