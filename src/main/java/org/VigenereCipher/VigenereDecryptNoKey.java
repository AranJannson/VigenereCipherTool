package org.VigenereCipher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        ArrayList<Integer> c3 = compare(stringToIntegerArray(c1), stringToIntegerArray(c2));

        try {
            //BufferReader Declaration
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //String value for current line
            String line;

            //Loop through the file
            while ((line = reader.readLine()) != null) {

                //Compare the current line to the difference of c1 - c2
                ArrayList<Integer> plainText2 = compare(stringToIntegerArray(line), c3);
                //String for plainText2 output
                String theWord;

                //plaintext2 as a String
                theWord = charArrayToString(intArrayToCharArray(plainText2));

                //Check if the word is located in the file
                if (line.equals(theWord)) {
                    //If successfully print
                    System.out.println("Success!!! " + theWord + " is in the file key is: ");
                    //End the program
                    System.exit(0);
                } else {
                    //If not found print
                    System.out.println("Failed: " + theWord + " Is not in the file");
                }


            }


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
     * Takes away 2 Arrays of Integers representing letters
     * Returning the difference of the 2 Integer Arrays
     *
     * @param array1, c1
     * @param array2, c2
     * @return outputArray
     */

    public ArrayList<Integer> compare(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        //Initialise array to be returned
        ArrayList<Integer> outputArray = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //Current letter to be fetched from array1
            int firstValue = array1.get(i);
            //Current letter to be fetched from array2
            int secondValue = array2.get(i);

            //Current values of array1 and array2 taken away from each other
            int firstMinusSecondVal = (firstValue - secondValue);

            //Check for negative values and out of bounds values
            if (firstMinusSecondVal < 0) {
                //If negative add 25
                firstMinusSecondVal += 25;
            } else if (firstMinusSecondVal >= 26) {
                //If bigger than the max alphabet character take away 25
                firstMinusSecondVal -= 25;
            }

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
