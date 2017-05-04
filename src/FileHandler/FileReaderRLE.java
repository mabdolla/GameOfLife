package FileHandler;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Game Of Life application created for HIOA
 * The Controller class is the fx for fxml, all the features in fxml are assigned in this class.
 * The class is also implementing Initializable interface.
 *
 * @author Fredrik, Hans Jacob, Mohammad
 *         Studentnr : S309293, s305064, s309856
 */
public class FileReaderRLE {
    StringBuilder lineBuilder = new StringBuilder();
    BufferedReader bReader;
    public File file2;

    public int[][] rules = new int[2][9];
    public int[][] brett;

    public FileReaderRLE(File file) throws IOException {
        this.file2 = file;

        if (file != null) {
            readBoard();
        }
    }

    /**
     * Reads a file from the computer.
     * @throws IOException
     */
    public void readBoard() throws IOException {
        // Reading file
        try {
            bReader = new BufferedReader(new FileReader(file2));
            String line; //midlertidig lagring av hver linje

            Pattern pattern = Pattern.compile("[xy]=(\\d+),[xy]=(\\d+),rule=(\\w\\d+)\\/(\\w\\d+)");

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

            while (ruleMatch.find()) {
                if (ruleMatch.group(1).matches("[bB]")) {
                    char[] dead = ruleMatch.group(2).toCharArray();
                    for (char c1 : dead) {
                        int i = Character.getNumericValue(c1);
                        rules[0][i] = 1;
                    }
                } else if (ruleMatch.group(1).matches("[ac-zAC-Z]")) {
                    char[] alive = ruleMatch.group(2).toCharArray();
                    for (char c2 : alive) {
                        int i = Character.getNumericValue(c2);
                        rules[1][i] = 1;
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
                    xlength = 1 + Integer.parseInt(sizeMatch.group(2));//adding start point of array x to 1 for adding space
                    System.out.println("xlength" + xlength);
                } else if (sizeMatch.group(1).matches("y")) {
                    ylength = 1 + Integer.parseInt(sizeMatch.group(2));//adding start point of array y to 1 for adding space
                    System.out.println("yLength" + ylength);
                }
            }

            brett = new int[xlength][ylength];

            //////////////////////////BOARD PATTERN//////////////////////
            int x = 0;
            int y = 0;

            Pattern pattern2 = Pattern.compile("(\\d+)?([ob\\$!])");
            Matcher matcher2 = pattern2.matcher(board);
            while (matcher2.find()) {

                if (matcher2.group(2).matches("b")) {
                    if (matcher2.group(1) == null) {
                        x++;
                    } else {
                        x += Integer.parseInt(matcher2.group(1));
                    }
                } else if (matcher2.group(2).matches("o")) {//HVIS fant at det skal være i live
                    if (matcher2.group(1) == null) {
                        brett[x][y] = 1;
                        x++;
                    } else { //Dersom det er et tall forran "o"
                        for (int i = x; x < (i + Integer.parseInt(matcher2.group(1))); x++) {
                            brett[x][y] = 1;//for hver "o" så må hver bli satt til live
                        }
                    }

                } else if (matcher2.group(2).matches("\\$")) {
                    if (matcher2.group(1) == null) {
                        y++;
                        x = 0;
                    } else {
                        y += Integer.parseInt(matcher2.group(1));
                        x = 0;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}