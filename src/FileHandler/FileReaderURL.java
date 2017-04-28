package FileHandler;

import javafx.scene.control.TextInputDialog;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Game Of Life application created for HIOA
 * The Controller class is the fx for fxml, all the features in fxml are assigned in this class.
 * The class is also implementing Initializable interface.
 *
 * @author Fredrik, Hans-Jacob, Mohammad
 * Studentnr : S309293,
 */
public class FileReaderURL {
    StringBuilder lineBuilder = new StringBuilder();
    BufferedReader bReader;
    public int[][] rules = new int[2][9];
    public int[][] brett;


    public void readBoardURL() {
        TextInputDialog dialog = new TextInputDialog("//");
        dialog.setTitle("URL FileReader");
        dialog.setHeaderText("Copy and paste url adress here");
        dialog.setContentText("URL:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("URL-adress: " + result.get());
        } else {
            System.out.println("URL-adress not found!");
        }

        try {
            URL url = new URL(result.get());
            bReader = new BufferedReader(new InputStreamReader(url.openStream()));

            Pattern pattern = Pattern.compile("([xy]=\\d+),([xy]=\\d+),rule=(\\w\\d+)\\/(\\w\\d+)");

            String Boardinfo = "";//her lagres
            StringBuilder board = new StringBuilder(); //her lagres patternet
            String line;
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
                        x += Integer.parseInt(matcher2.group(1));
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
