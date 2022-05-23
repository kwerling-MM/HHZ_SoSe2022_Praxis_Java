package com.hhz;

public class Main {

    public static void main(String[] args) {

        ManageDB db = new ManageDB();

        int i = 0;
        while( i < args.length ) {
            db.add( args[i], args[i+1]);
            i ++;

            i++;
        }

        db.printResult();
        db.save();

    }
}
