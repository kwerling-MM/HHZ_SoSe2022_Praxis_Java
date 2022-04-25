package com.hhz;

import com.hhz.Converter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            if(args.length == 0) throw(new Exception("Converter <Filename>"));

            Converter converter = new Converter();
            File dataFile = null;
            File newFile = null;
            FileWriter file = null;
            ArrayList<String> orgText = new ArrayList<String>();
            Scanner sc = null;

            dataFile = new File(args[0]);
            sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                orgText.add(sc.nextLine());
            }
             sc.close();


            ArrayList<String> newText = converter.convert(orgText);

            String fName = args[0].substring(0, args[0].lastIndexOf('.')) + ".latex";

            newFile = new File(fName);
            file = new FileWriter(newFile);
            for (int i = 0; i < newText.size(); i++) {
                file.write(newText.get(i) + '\n');
            }
            file.close();

         } catch (Exception ex) {
            System.err.println("ERROR:  " + ex.getMessage());
        }


    }
}
