package FileHandler;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fredrik Kluftødegård on 18.04.2017.
 */
public class FileReaderURL {
    FileChooser fChooser = new FileChooser(); //åpner explorer og lar bruker velge fil
    File file2;//dette er filen, ikke enda
    java.io.FileReader fReader;
    BufferedReader bReader;
    StringBuilder lineBuilder = new StringBuilder();
    public int[][] brett;
    public int[][] rules = new int[2][9];


    public void readBoardURL() {

        TextInputDialog inputbox = new TextInputDialog("URL-adress");
        Optional<String> result = inputbox.showAndWait();



        if (file2 != null) {
            System.out.println("You choose this file");
        } else {
            System.out.println("File not found");
        }


        // Reading file
        try {
            bReader = new BufferedReader(new java.io.FileReader(file2));
            String line; //midlertidig lagring av hver linje

            Pattern pattern = Pattern.compile("([xy]=\\d+),([xy]=\\d+),rule=(\\w\\d+)\\/(\\w\\d+)");

            String Boardinfo = "";//her lagres (x=??,y=??,rules=B2/S23
            StringBuilder board = new StringBuilder(); //her lagres patternet

            while ((line = bReader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                line = line.replaceAll("\\s", "");   //fjerner mellomrom
                lineBuilder.append(line).append("\n"); //deler linje for linje

                if (pattern.matcher(line).matches())
                    Boardinfo = line;
                else
                    board.append(line);
            }
            System.out.println("RULES: " + Boardinfo);
            System.out.println(board.toString());



            ///////////////////////RULES/////////////////////////////

            Pattern findRules = Pattern.compile("(\\w)(\\d+)");
            Matcher ruleMatch = findRules.matcher(Boardinfo);

            while (ruleMatch.find()){
                if (ruleMatch.group(1).equals("B")){
                    System.out.println("FUNNET REGLER");
                    char[] dead = ruleMatch.group(2).toCharArray();

                    for (char c1 : dead) {
                        int i = Character.getNumericValue(c1);
                        rules[0][i] = 1;
                    }
                }else if (ruleMatch.group(1).equals("S")) {
                    char [] alive = ruleMatch.group(2).toCharArray();
                    for (char c2 : alive) {
                        int i = Character.getNumericValue(c2);
                        rules [1][i] = 1;
                    }

                }

            }


            /////////////////////BOARD LENGTH/////////////////////////
            int xlength = 0;
            int ylength = 0;

            Pattern findSize = Pattern.compile("([xy])=(\\d+)");
            Matcher sizeMatch = findSize.matcher(Boardinfo);

            while (sizeMatch.find()) {
                if (sizeMatch.group(1).matches("x")) {
                    xlength = 1+Integer.parseInt(sizeMatch.group(2));//adding start point of array x to 1 for adding space
                } else if (sizeMatch.group(1).matches("y")) {
                    ylength = 1+Integer.parseInt(sizeMatch.group(2));//adding start point of array y to 1 for adding space
                }
            }

            brett = new int[xlength][ylength];
            System.out.println(xlength);
            System.out.println(ylength);



            //////////////////////////BOARD PATTERN//////////////////////
            int x = 0;
            int y = 0;

            Pattern pattern2 = Pattern.compile("(\\d+)?([ob\\$!])");
            Matcher matcher2 = pattern2.matcher(board);
            while (matcher2.find()) {

                if (matcher2.group(2).matches("b")) {
                    if (matcher2.group(1)==null) {
                        x++;
                    } else {
                        x = Integer.parseInt(matcher2.group(1));
                    }
                } else if (matcher2.group(2).matches("o")) {
                    if (matcher2.group(1)==null) {
                        brett[y][x] = 1;
                        x++;
                    } else {
                        for (int i = x; x < (i+Integer.parseInt(matcher2.group(1))); x++) {
                            brett[y][x] = 1;
                        }
                    }

                } else if (matcher2.group(2).matches("\\$")) {
                    if (matcher2.group(1)==null) {
                        y++;
                        x = 0;
                    } else {
                        y=Integer.parseInt(matcher2.group(1));
                        x = 0;
                    }
                }
            }


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

}
