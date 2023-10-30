package org.VigenereCipher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;


public class VigenereDecryptNoKey {

    String c1, c2;
    HashMap<Character, Integer> alphabet;
    HashMap<Integer, Character> inverseAlphabet;
    Alphabet alphabetClass = new Alphabet();

    public VigenereDecryptNoKey(){

        alphabet = alphabetClass.getAlphabet();
        inverseAlphabet = alphabetClass.getInverseAlphabet();
    }

    public void decryptNoKey(String c1, String c2) throws FileNotFoundException {

        String file = "10letterwordslist.txt";

        ArrayList<Integer> c3 = compare(stringToIntegerArray(c1), stringToIntegerArray(c2));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while((line = reader.readLine()) != null) {

                ArrayList<Integer> plainText2 = compare(stringToIntegerArray(line), c3);
                String theWord;

                theWord = charArrayToString(intArrayToCharArray(plainText2));

                if(line.equals(theWord)){
                    System.out.println("Success!!! " + theWord + " is in the file key is: ");
                    System.exit(0);
                }else{
                    System.out.println("Failed: " + theWord + " Is not in the file");
                }


            }


        } catch (IOException e) {
            System.out.println("IOException caught");
        }catch (NullPointerException e){
            System.out.println("END");
        }


    }

    public ArrayList<Integer> compare(ArrayList<Integer> array1, ArrayList<Integer> array2){
        ArrayList<Integer> outputArray = new ArrayList<>();

        for(int i =0; i < 10; i++){
            int firstValue = array1.get(i);
            int secondValue = array2.get(i);

            int firstMinusSecondVal = (firstValue - secondValue);

            if (firstMinusSecondVal < 0) {
                firstMinusSecondVal += 25;
            } else if (firstMinusSecondVal >= 26) {
                firstMinusSecondVal -= 25;
            }

            outputArray.add(firstMinusSecondVal);

        }


        return outputArray;
    }

    public ArrayList<Character> intArrayToCharArray(ArrayList<Integer> array){

        ArrayList<Character> outputArray = new ArrayList<>();

        for(int i : array){
            outputArray.add(inverseAlphabet.get(i));
        }

        return outputArray;


    }

    public ArrayList<Integer> stringToIntegerArray(String input){

        ArrayList<Integer> outputArray = new ArrayList<>();

        for(char i : input.toCharArray()){
            outputArray.add(alphabet.get(i));
        }

        return outputArray;


    }

    public String charArrayToString(ArrayList<Character> array){

        StringBuilder buffer = new StringBuilder();

        for(char i : array){
            buffer.append(i);
        }

        return buffer.toString();

    }

    public ArrayList<Integer> charArrayToIntArray(ArrayList<Character> array){
        ArrayList<Integer> outputArray = new ArrayList<>();

        for(char i : array){
            outputArray.add(alphabet.get(i));
        }

        return outputArray;

    }
}
