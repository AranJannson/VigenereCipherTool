package org.VigenereCipher;

import java.util.HashMap;


/**
 * Generate and return the standard and inverse Alphabet
 *
 * @author Aran Jannson
 */

public class Alphabet {

    //HashMap for the alphabet <letter, id>
    private final HashMap<Character, Integer> alphabetMap = new HashMap<>();
    //HashMap for the inverse alphabet <id, letter>
    private final HashMap<Integer, Character> inverseAlphabetMap = new HashMap<>();
    //Alphabet String
    private final String alphabet;

    public Alphabet() {

        //Initialise alphabet String
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //Create the alphabet
        this.generateAlphabet(this.alphabet);

    }

    /**
     * Creates the alphabet in an HashMap form from the Alphabet String
     *
     * @param alphabet, String with the charater set
     */

    private void generateAlphabet(String alphabet) {
        //Counter
        int count = 0;

        //Create both regular and inverse alphabet from the char array of the alphabet string
        for (char i : alphabet.toCharArray()) {
            //Add the current letter into the alphabet
            alphabetMap.put(i, count);
            //Add the current letter into the inverse alphabet
            inverseAlphabetMap.put(count, i);
            //Increment counter
            count++;
        }
    }

    /**
     * Get the alphabet
     *
     * @return alphabetMap, Hashmap of the alphabet
     */

    public HashMap<Character, Integer> getAlphabet() {
        return alphabetMap;
    }

    /**
     * Get the inverse alphabet
     *
     * @return inverseAlphabetMap, Hashmap of the inverse alphabet
     */

    public HashMap<Integer, Character> getInverseAlphabet() {
        return inverseAlphabetMap;
    }
}
