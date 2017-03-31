package FileHandler;

import javafx.stage.FileChooser;

import java.io.*;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fredrik Kluftødegård on 31.03.2017.
 */
public class FileReaderRLE {
    FileChooser fChooser = new FileChooser();
    File file2;
    java.io.FileReader fReader;
    BufferedReader bReader;
    StringBuilder lineBuilder = new StringBuilder();

    public void readBoard()  {
        file2 = fChooser.showOpenDialog(null);
        if (file2 != null) {


            System.out.println("You choose this file");
        } else {

            System.out.println("File not found");
        }

        // Reading file
        try {

            fReader = new FileReader(file2);
            bReader = new BufferedReader(fReader);
            String line;

            Pattern pattern = Pattern.compile("([xy]=\\d+),([xy]=\\d+),rule=(\\w\\d+)\\/(\\w\\d+)");
            Pattern boardPattern = Pattern.compile("(\\d+)?([bo]+)\\$|(\\d+)([bo]+)!?");

            String Boardinfo = "";
            StringBuilder board = new StringBuilder();




            while ((line = bReader.readLine()) != null) {
                if (line.startsWith("#")){
                continue;
                }

                line = line.replaceAll("\\s", "");   //fjerner mellomrom
                    lineBuilder.append(line).append("\n"); //deler linje for linje

                if( pattern.matcher(line).matches())
                    Boardinfo = line;
                else
                    board.append(line);
            }
            System.out.println("RULES: " + Boardinfo);
            System.out.println(board.toString());


            int xlength = 0;
            int ylength = 0;

            Pattern p2 = Pattern.compile("([xy])=(\\d+)");
            Matcher m2 = p2.matcher(Boardinfo);

            while(m2.find()){
                if (m2.group(1).matches("x")) {
                    xlength = Integer.parseInt(m2.group(2));
                }else if(m2.group(1).matches("y")){
                    ylength = Integer.parseInt(m2.group(2));

                }

            }
            int [][] brett = new int[ylength][xlength];
            System.out.println(xlength);
            System.out.println(ylength);

            System.out.println(Arrays.deepToString(brett));


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}

