package org.VigenereCipher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;


/**
 * Vigenere Cipher Decrypt
 * When only 2 ciphertexts and a large pool of plaintexts are given
 *
 * @author Aran Jannson
 */

public class VigenereDecryptNoKey {

    //Ciphertext 1 and 2
    String c1, c2;
    //HashMap with the alphabet <letter, id>
    HashMap<Character, Integer> alphabet;
    //HashMap with the inverse alphabet <id, letter>
    HashMap<Integer, Character> inverseAlphabet;
    //Alphabet Object
    Alphabet alphabetClass = new Alphabet();
    //ArrayList to contain c1 - c2
    ArrayList<Integer> c3;

    public VigenereDecryptNoKey() {

        //Initialise the alphabet
        alphabet = alphabetClass.getAlphabet();
        //Initialise the inverse alphabet
        inverseAlphabet = alphabetClass.getInverseAlphabet();


    }

    /**
     * Using the difference between 2 ciphertexts c1 and c2
     * Then taking it away from the Integer representation of Plaintexts in '10letterwordslist.txt'
     * It returns Plaintext 2 there for decrypting c2 which then gets c1 and the key respectively
     *
     * @param c1, ciphertext 1
     * @param c2, ciphertext 2
     * @throws FileNotFoundException, File was unable to opened
     */

    public void decryptNoKey(String c1, String c2) throws FileNotFoundException {

        //File with plaintext
        String file = "10letterwordslist.txt";

        //Array of the difference between c1 and c2
        this.c3 = differenceBetweenCiphers(stringToIntegerArray(c1), stringToIntegerArray(c2));

        try {
            //BufferReader Declaration
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            //String value for current line
            String line;

            System.out.println("Processing...");

            //Loop through the file
            while ((line = bufferedReader.readLine()) != null) {
                //Compare the current line with c1 - c2
                String newWord = charArrayToString(intArrayToCharArray(compare(line)));

                //Check if newWord is located in the file
                if (Files.lines(Paths.get(file)).anyMatch(innerLine -> innerLine.contains(newWord))) {
                    //If newWord return this message along with the key
                    System.out.println("Success!!! " + newWord + " is in the file!!!");
                    System.out.println("The Key is: " + generateKey(newWord, c1));
                    // End the program
                    System.exit(0);
                }
            }

            bufferedReader.close();
            //Check for an IOException
        } catch (IOException e) {
            //Print out 'IOException caught'
            System.out.println("IOException caught");

            //Check for NullPointerExceptions
        } catch (NullPointerException e) {
            //Print out 'END'
            System.out.println("END");
        }


    }

    /**
     * Generate the key by taking away the value of the ciphertext from the plaintext
     *
     * @param p1, plaintext1
     * @param c1, ciphertext1
     * @return outputArray
     */


    public String generateKey(String p1, String c1) {

        //ArrayList to store int values of p1
        ArrayList<Integer> p1IntArray = stringToIntegerArray(p1);
        //ArrayList to store int values of c1
        ArrayList<Integer> c1IntArray = stringToIntegerArray(c1);

        //ArrayList to hold the key in int form
        ArrayList<Integer> outputArray = new ArrayList<>();

        //Loop through word size (Default array length of 10 for this requirement)
        for (int i = 0; i < 10; i++) {

            //Take away ciphertext1 from plaintext1
            int newCharacter = c1IntArray.get(i) - p1IntArray.get(i);


            //Check if the int value of the letter is valid
            if (newCharacter >= 26) {
                //If the int value is above 25 (max val of alphabet) do mod 26
                newCharacter %= 26;
            }
            if (newCharacter < 0) {
                //If the int value of the character is in the negatives add 26
                newCharacter += 26;
            }

            //Add the current int character to the outputArray
            outputArray.add(newCharacter);

        }

        //Return the string version of outputArray
        return charArrayToString(intArrayToCharArray(outputArray));

    }

    /**
     * Takes away 2 Arrays of Integers representing letters
     * Returning the difference of the 2 Integer Arrays
     *
     * @param word, word to compare with c1 - c2
     * @return outputArray
     */

    public ArrayList<Integer> compare(String word) {

        //Arraylist with the integer values of the word
        ArrayList<Integer> wordIntArray = stringToIntegerArray(word);
        //ArrayList to be returned with the result
        ArrayList<Integer> outputArray = new ArrayList<>();
        //ArrayList containing the shift values of c1 - c2
        ArrayList<Integer> shifts = this.c3;
        //Loop through word size (Default word length of 10 for this requirement)
        for (int i = 0; i < 10; i++) {
            //Shift the current letter
            int newCharacter = shifts.get(i) + wordIntArray.get(i);

            //Check if the int value of the letter is valid
            if (newCharacter >= 26) {
                //If the int value is above 25 (max val of alphabet) do mod 26
                newCharacter %= 26;
            }
            if (newCharacter < 0) {
                //If the int value of the character is in the negatives add 26
                newCharacter += 26;
            }

            //Add the current int character to the outputArray
            outputArray.add(newCharacter);
        }

        //Return outputArray
        return outputArray;

    }

    /**
     * Get the shift difference between the 2 initial ciphers
     *
     * @param array1, c1
     * @param array2, c2
     * @return outputArray
     */

    public ArrayList<Integer> differenceBetweenCiphers(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        //Initialise array to be returned
        ArrayList<Integer> outputArray = new ArrayList<>();

        //Loop through the size of the array's (Default array length of 10 for this requirement)
        for (int i = 0; i < 10; i++) {
            //Current letter to be fetched from array1
            int firstValue = array1.get(i);
            //Current letter to be fetched from array2
            int secondValue = array2.get(i);
            //Current values of array1 and array2 taken away from each other
            int firstMinusSecondVal = (firstValue - secondValue);
            //Add the value found into an ArrayList outputArray
            outputArray.add(firstMinusSecondVal);

        }

        //Return the ArrayList containing the shift difference between array1 and array2
        return outputArray;
    }

    /**
     * Converts Integer ArrayLists into Integer ArrayLists
     *
     * @param array, inputted ArrayList
     * @return outputArray
     */

    public ArrayList<Character> intArrayToCharArray(ArrayList<Integer> array) {
        //Initialise array to be returned
        ArrayList<Character> outputArray = new ArrayList<>();

        //Loop through input as an array of Integers
        for (int i : array) {
            //Add the current Integers into the ArrayList
            outputArray.add(inverseAlphabet.get(i));
        }

        //Return the ArrayList
        return outputArray;


    }

    /**
     * Turns Strings into an ArrayList of Characters
     *
     * @param input, The inputted string
     * @return outputArray
     */

    public ArrayList<Integer> stringToIntegerArray(String input) {
        //Initialise array to be returned
        ArrayList<Integer> outputArray = new ArrayList<>();

        //Loop through input as an array of characters
        for (char i : input.toCharArray()) {
            //Add the current character into the ArrayList
            outputArray.add(alphabet.get(i));
        }

        //Return the ArrayList
        return outputArray;


    }

    /**
     * Turns Character ArrayLists into Strings
     *
     * @return buffer
     */

    public String charArrayToString(ArrayList<Character> array) {
        //Initialise StringBuilder to be returned
        StringBuilder buffer = new StringBuilder();

        //Loop through the ArrayList
        for (char i : array) {
            //Add the current Character in the ArrayList to the StringBuilder
            buffer.append(i);
        }

        //Return the StringBuilder as a String
        return buffer.toString();

    }
}
