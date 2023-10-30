package org.VigenereCipher;

import java.io.FileNotFoundException;

public class Main {

    static String c1 = "IHRXMTLNTS";
    static String c2 = "TRKEYTNWLD";

    public static void main(String[] args) throws FileNotFoundException {


        VigenereDecryptNoKey vigenere = new VigenereDecryptNoKey();

        vigenere.decryptNoKey(c1,c2);

    }

}


