package FileHandler;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * The Game Of Life application created for Oslo and Akershus University Collage of Applied Sciences.
 * The FileReader class is the class for reading and parsing txt files containing board patterns.
 *
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
 *         Studentnr : S309293, s305064, s309856
 */
public class FileReader {


    /**
     * This method sets up a filechooser, which is a fileexplorer, that gets access to see txt files on the computer.
     * Filechooser is created and then ads extentionfilter with the formats available.
     *
     * @return fileChooser that creates an fileexplorer that allows the user to upload files from computer.
     */
    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load txt file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("PlainText"), ("RLE"), ("*.rle"), ("*.txt")));

        return fileChooser;
    }

    /**
     * This method opens a TXT file and parses the file into an array.
     * A scanner is created with the parameter txtfile.
     * While loop checks gets line by line in the file chosen.
     * Creates an array that thakes x and y parameters.
     * in the for loop, if a character is 'o', it gets the value 1, meaning alive.
     *
     * @return new int[][] array which is the new board pattern that will show up on the board.
     */
    public static int[][] openTXTfile() {

        try {
            File txtfile = getFileChooser().showOpenDialog(null);
            Scanner in = new Scanner(txtfile);
            int ylength = 0;
            int xlength = 0;
            String length;

            while (in.hasNext()) {
                length = in.nextLine();
                if ((length.length()) > ylength) {            //Gets line by line in file chosen
                    if (!length.startsWith("!"))
                        ylength = length.length();           //Set number of total line as x value
                }
                xlength++;

            }

            int[][] brett = new int[xlength][ylength];        //Creates an array that takes x and y as parameters

            in = new Scanner(txtfile);
            int y = 0;
            while (in.hasNext()) {
                length = in.nextLine();
                if (!length.startsWith("!")) {                    //Skips lines starting with !
                    for (int x = 0; x < length.length(); x++) {   //Loop that reads every line

                        char a = length.charAt(x);   //if character in the line is 'o', it get the value 1, meaning alive.
                        if (a == 'O') {
                            brett[y][x] = 1;
                        } else {
                            brett[y][x] = 0;
                        }
                    }
                    y++;
                }
            }
            for (int[] ints : brett) {

                System.out.println(Arrays.toString(ints));
            }

            return brett;

        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
        return new int[0][0];
    }

}













