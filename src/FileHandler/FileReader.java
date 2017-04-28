package FileHandler;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * The Game Of Life application created for HIOA
 * The Controller class is the fx for fxml, all the features in fxml are assigned in this class.
 * The class is also implementing Initializable interface.
 *
 * @author Fredrik, Hans-Jacob, Mohammad
 * Studentnr : S309293,
 */
public class FileReader  {

    private static FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load txt file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(("PlainText"),("RLE"), ("*.rle"),( "*.txt")));

        return fileChooser;
    }

    public static int[][] openTXTfile(){

            File txtfile = getFileChooser().showOpenDialog(null);

        try {
                Scanner in = new Scanner(txtfile);
                int ylength = 0;
                int xlength = 0;
                String length;

                while (in.hasNext()) {
                    length = in.nextLine();
                    if((length.length()) > ylength){            //henter linje for linje
                        if (!length.startsWith("!"))
                            ylength = length.length();           //setter antall linjer som en x verdi
                    }
                    xlength++;

                }

                System.out.println(xlength+" x length, "+ylength+" y length");
                int [][] brett= new int [xlength][ylength];        //opretter array som tar imot y og x lengde

                in = new Scanner(txtfile);
                int y = 0;
                while (in.hasNext()) {
                    length = in.nextLine();
                    if (!length.startsWith("!")){
                        for (int x = 0; x < length.length(); x++) {   //løkke som leser hver linje

                            char a = length.charAt(x);   //er en character på linja 0, får den verdi 1(levende)
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


            } catch (FileNotFoundException e) {



            }return null;
        }

}













