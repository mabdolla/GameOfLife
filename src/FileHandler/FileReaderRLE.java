package FileHandler;

import java.io.*;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Game Of Life application created for Oslo and Akershus University Collage of Applied Sciences.
 * The FileReaderRLE class parses both rle files from computer and rle files from url address,
 * and creates an arraylist that will be the new board pattern.
 *
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
 *         Studentnr : S309293, s305064, s309856
 */
public class FileReaderRLE {
    public File file2;
    public int[][] rules = new int[2][9];
    public int[][] brett;
    StringBuilder lineBuilder = new StringBuilder();
    BufferedReader bReader;


    /**
     * Reads a file from the computer.
     *
     * @param file The file name for the file uploaded from disk.
     *             If the file is not empty, the readboard method is called.
     * @throws IOException Throws index out of bounds exeption if the filepattern is out of array bounds.
     */
    public FileReaderRLE(File file) throws IOException {
        this.file2 = file;

        if (file != null) {
            readBoard();
        }
    }

    /**
     * Parses a file from the computer and creates an array with xlength & ylength according to the RLE file patterns size.
     * Regex is used to parse pattern, and converting the content of the file to cells either dead or alive
     * The file is returned as an arraylist that is the new boardpattern.
     * The while loop checks if the file has content according to regex and performs the expressions if they match with matcher.
     *
     * @throws IOException
     * @see jdk.nashorn.internal.runtime.regexp.joni.Regex
     */
    public void readBoard() throws IOException {


        // Starts the process reading the file
        try {
            bReader = new BufferedReader(new FileReader(file2));
            String line;                                    //Temporarily storing of each line from txt file

            Pattern pattern = Pattern.compile("[xy]=(\\d+),[xy]=(\\d+),rule=(\\w\\d+)\\/(\\w\\d+)");

            String Boardinfo = "";
            StringBuilder board = new StringBuilder();       //This will save the pattern temporarily

            while ((line = bReader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                line = line.replaceAll("\\s", "");          //Removes space between letters in the file
                lineBuilder.append(line).append("\n");      //Dividing line by line

                if (pattern.matcher(line).matches())
                    Boardinfo = line;
                else
                    board.append(line);
            }

            //Printed out info from RLE file, boardsize x length and y length.
            //System.out.println("INFO: " + Boardinfo);


            ///////////////////////RULES/////////////////////////////

            //Here are the rules from RLE files parsed into and array with rules.

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

            //Finding the needed size on array for the board from the RLE file.

            int xlength = 0;
            int ylength = 0;

            Pattern findSize = Pattern.compile("([xy])=(\\d+)");
            Matcher sizeMatch = findSize.matcher(Boardinfo);

            while (sizeMatch.find()) {
                if (sizeMatch.group(1).matches("x")) {
                    xlength = 1 + Integer.parseInt(sizeMatch.group(2));//adding start point of array x to 1 for adding space

                } else if (sizeMatch.group(1).matches("y")) {
                    ylength = 1 + Integer.parseInt(sizeMatch.group(2));//adding start point of array y to 1 for adding space

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
                    //if "o" is found, the cell would be alive

                } else if (matcher2.group(2).matches("o")) {
                    if (matcher2.group(1) == null) {
                        brett[x][y] = 1;
                        x++;
                    } else {

                        //If it is a number in the front of "o", thats the number of cells that would be alive

                        for (int i = x; x < (i + Integer.parseInt(matcher2.group(1))); x++) {
                            brett[x][y] = 1; //setting the "o" alive to the array
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