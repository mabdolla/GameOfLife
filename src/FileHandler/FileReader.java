package FileHandler;

import Controller.GameOfLifeController;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fredrik Kluftødegård on 24.03.2017.
 */
public class FileReader  {


    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load txt file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PlainText", ("*.txt")));


        return fileChooser;
    }

    public static int [][] openFromFile() {

        File file = getFileChooser().showOpenDialog(null);


        try {
            Scanner in = new Scanner(file);
            int ylength = 0;
            int xlength = 0;
            String length = in.nextLine(); //henter linje


            while (in.hasNext()) {

                if(in.nextLine().length()> xlength){
                    xlength = in.nextLine().length();

                }
                /*  for(int x = 0; x < length.length();x++){

                  char a = length.charAt(x);
                    if(a == 0){
                        brett[y][1];
                    }else{

                    }
                }*/
                ylength++;
            }


            int [][] brett= new int [xlength][ylength];

            int y = 0;
            while (in.hasNext()) {
                  for(int x = 0; x < length.length();x++){

                  char a = length.charAt(x);
                    if(a == 0){
                        brett[y][x] = 1;
                    }else{
                        brett[y][x] = 0;
                    }
                }
                y++;
            }
            return brett;






        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filen er ikke funnet");

        }return null;
    }
}



       /*if (file == null) {
            return null;
        }else{
       System.out.println("Filen er åpnet");
        return file;

    }*/


   /* public static File readFile(){

        try {
            BufferedReader reader = new BufferedReader(file);
        } catch (IOException ex){

        }

        String text = "";
        String line = br.readLine;
        while (line != null){
            text += line;
            line = br.re
        }

    }*/






   /* public static Brett loadFromFile(File file, int defaultSize) {
        String input = "";
        int sz = defaultSize;
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine().replaceAll("\\s+","");
                input += line;

                sz = line.length();
            }
        } catch (FileNotFoundException e) {
            // should never happen since we return on null file
            // so if we end up here it's something really bad
            // and so we let it blow up to runtime
            throw new RuntimeException(e);
        }

        Brett[][] g = new Brett[sz][sz];
        System.out.println(g);


        int pos = 0;
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                boolean state = (input.charAt(pos) =='1');
               // g[i][j] = new Brett(state);
                pos++;
            }
        }

        return new Brett(50, 50);
    }

       /* public void fileChooser (){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");


            FileChooser.ExtensionFilter extFilt = new FileChooser.ExtensionFilter("PlainText", ("*.txt"));
            extFilt.getDescription();

            System.out.println(extFilt.getDescription());

            fileChooser.getExtensionFilters().add(extFilt);

            File file = fileChooser.showOpenDialog(null);

            /*if (file != null) {
                GameOfLifeController.listView.getItems().addAll(file.getName(), extFilt.getDescription());
            } else {
                System.out.println("Filen er ikke godtatt");
            }*/




    /*private String readFile(File file) {
        StringBuilder stringreader = new StringBuilder();
        BufferedReader bufferedReader = null;

    } */

