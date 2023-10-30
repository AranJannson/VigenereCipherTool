package org.example;

import java.io.FileNotFoundException;

public class Main {

    static String c1 = "IHRXMTLNTS";
    static String c2 = "TRKEYTNWLD";

    public static void main(String[] args) throws FileNotFoundException {


        VigenereDecryptNoKey viegenere = new VigenereDecryptNoKey();

        viegenere.decryptNoKey(c1,c2);

    }

}


