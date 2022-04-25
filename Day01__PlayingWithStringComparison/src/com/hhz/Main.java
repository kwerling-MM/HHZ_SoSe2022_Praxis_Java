package com.hhz;

public class Main {

    public static void main(String[] args) {

        System.out.println("Explain the following results:" );

        // Bad : Variables are to be declared on top of the block

        String S1 = "Hello World";
        String S2 = S1;
        System.out.println( " S1 and S2 are equal: "  +  ( S1 == S2 ) );

        String S3 = "Hello World";
        System.out.println( " S1 and S3 are equal: "  +  ( S1 == S3 ) );
        System.out.println( " S2 and S3 are equal: "  +  ( S2 == S3 ) );

        String S4 = new String("Hello World");
        System.out.println( " S1 and S4 are equal: "  +  ( S1 == S4 ) );
        // System.out.println( " S1: "  +  ">" + S1 + "<" );
        // System.out.println( " S4: "  +  ">" + S4 + "<" );

        String S5 = "Hello " + "World";
        System.out.println( " S1 and S5 are equal: "  +  ( S1 == S5 ) );

        String S6 = "Hello ";
        S6 = S6 + "World";
        System.out.println( " S1 and S6 are equal: "  +  ( S1 == S6 ) );
    }
}
