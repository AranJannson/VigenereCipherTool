package org.example;

import java.util.HashMap;

public class Alphabet {

    private final HashMap<Character, Integer> alphabetMap = new HashMap<>();
    private final HashMap<Integer, Character> inverseAlphabetMap = new HashMap<>();
    private final String alphabet;

    public Alphabet(){

        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        this.generateAlphabet(this.alphabet);

    }

    private void generateAlphabet(String alphabet){
        int count = 0;

        for(char i : alphabet.toCharArray()){
            alphabetMap.put(i, count);
            inverseAlphabetMap.put(count, i);
            count++;
        }
    }

    public HashMap<Character, Integer> getAlphabet() {
        return alphabetMap;
    }

    public HashMap<Integer, Character> getInverseAlphabet(){
        return inverseAlphabetMap;
    }
}
