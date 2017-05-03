package Dialog;

import javafx.scene.control.Alert;

/**
 * Created by Fredrik Kluftødegård on 03.05.2017.
 */
public interface Dialogboxes {

   static void gameInformation() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Game of Life");
        alert.setHeaderText("Game of life rules");
        alert.setContentText("Source Wikipedia:\n\n" +
                "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.\n" +
                "The \"game\" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input."+
                "You can interact in this java version of the game by drawing alive cells on the screen and see how they evolve." +
                "\n\n"+
                "Every cell thats drawn interacts with its eight neighbours that each cell has"+
                "Based on this rules the cells evolves into further generations:"+

                "RULES:\n\n"+
                        "1: Any live cell with fewer than two alive neighbours dies, as if caused by underpopulation.\n\n" +
                        "2: Any live cell with two or three alive neighbours lives on to the next drawGeneration.\n\n" +
                        "3: Any live cell with more than three alive neighbours dies, as if by overpopulation.\n\n" +
                        "4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\n\n URL link: " + "https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life");

        alert.showAndWait();
    }

    /**
     * This method creates a alertbox.
     */
    static void drawError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Oooops!");
        alert.setContentText("You cant draw outside the screen, please try again");

        alert.showAndWait();
    }

    static void filenotFoundError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("ERROR!");
        alert.setContentText("This is not a valid URL-adress, please try again!");

        alert.showAndWait();
    }
}
