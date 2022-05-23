package com.hhz;

import com.kwerlingit.MemKV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ManageDB {

    final String dbName = "myDB.kv";

    MemKV mkv = new MemKV();

    ManageDB() {

        if(Files.exists(Paths.get( dbName ))) {
            try {
                mkv.load( dbName );
            } catch (Exception e) {
                System.err.println( "ERROR: " + e.toString());
            }
        }
    }

    void add( String name, String val ) {

        String v = "";

        if( mkv.containsKey( name )  )  v = mkv.getString( name ) + "@" + val ;
        else v = val;

        try {
            mkv.put( name, v);
        } catch (Exception e) {
           System.err.println("ERROR: " + e.toString());
        }
    }

    void printResult() {
        mkv.forEach((k, v) -> {
            String[] sArr = ((String) v).split("@");
            int l = sArr.length;
            int[] pointsArr = new int[ l ];
            for( int i = 0; i < l; i ++) pointsArr[i] = Integer.parseInt( sArr[i]);

            int sum = 0;
            for( int i = 0; i < l; i ++) sum += pointsArr[i];

            System.out.print( k + "  " + sum + "  ("); System.out.print(pointsArr[0]);
            for( int i = 1; i < l; i ++) System.out.print( ", " + pointsArr[i]);
            System.out.println( ")");
        });
    }

    void save(){
        try {
            mkv.persist( dbName);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.toString());
        }
    }
}
