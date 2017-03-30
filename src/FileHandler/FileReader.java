package FileHandler;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Scanner;


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
            String length = in.nextLine();


            while (in.hasNext()) {

                if(in.nextLine().length()> xlength){            //henter linje for linje
                    xlength = in.nextLine().length();           //setter antall linjer som en x verdi

                }
                ylength++;
            }


            int [][] brett= new int [xlength][ylength];        //opretter array som tar imot y og x lengde

            int y = 0;
            while (in.hasNext() ) {
                  for(int x = 0; x < length.length();x++){   //løkke som leser hver linje

                  char a = length.charAt(x);                //er en character på linja 0, får den verdi 1(levende)
                    if(a == '0'){
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












