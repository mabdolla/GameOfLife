package InterfaceDialog;

import javafx.scene.control.Alert;

/**
 * The Game Of Life application created for HIOA 2017
 * The Dialogboxes interface contains methods creating alertboxes with information and exeption handling.
 *
 * @author Fredrik, Hans Jacob, Mohammad
 *         Studentnr : S309293, s305064, s309856
 */
public interface Dialogboxes {

    /////////////////////////////////INFORMATION ABOUT THE GAME & HOW TO PLAY BOXES/////////////////////////////////////

    static void gameInformation() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Game of Life");
        alert.setHeaderText("Game of life rules");
        alert.setContentText("Source Wikipedia:\n\n" +
                "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.\n" +
                "The \"game\" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input." +
                "You can interact in this java version of the game by drawing alive cells on the screen and see how they evolve." +
                "\n\n" +
                "Every cell thats drawn interacts with its eight neighbours that each cell has based on these rules the cells evolves into further generations:" +

                "RULES:\n" +
                "1: Any alive cell with less than two alive neighbours dies, as in underpopulation.\n\n" +
                "2: Any alive cell with two or three alive neighbours lives on.\n\n" +
                "3: Any alive cell with more than three alive neighbours dies, as in by overpopulation.\n\n" +
                "4: Any dead cell with exactly three alive neighbours turns into a living cell, as in reproduction.\n\n" +

                "Source URL link: " + "https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life");

        alert.showAndWait();
    }

    static void howToPlayInfo() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to play");
        alert.setHeaderText("How to play and interact with Game Of Life application");
        alert.setContentText(
                        "DRAWING PATTERNS:\n" +
                        "Click your left mouse to draw alive cells on the grid. If you want to make cells dead, simply drag or click over them again.\n\n" +

                        "START & STOP BUTTON:\n" +
                        "Starts the animation of how the cells will evolve according to the rules from Conway`s Game Of Life. To stop the game simply click STOP.\n\n" +

                        "CLEAR BOARD:\n" +
                        "By clicking this button, the grid will clear and you will start with a blank drawing grid.\n\n" +

                        "ZOOM:\n" +
                        "Zooming in make the gridcells bigger, zooming out makes the gridcells smaller.\n\n" +

                        "ADJUST SPEED:\n" +
                        "Changing the speed of the animation.\n\n" +

                        "BACKGROUND COLOR:\n" +
                        "Changing the background color on the grid.\n\n" +

                        "CELL COLOR:\n" +
                        "Changing the color of the cells alive.\n\n" );

        alert.showAndWait();
    }



    //////////////////////////////ALERT BOXES FOR EXEPTION HANDLING////////////////////////////////////////////////////
    /**
     * This method creates a alertbox for exeption in userDrawcell method in controller.
     */
    static void drawError() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Oooops!");
        alert.setContentText("You cant draw outside the screen, please try again");

        alert.showAndWait();
    }

    /**
     * This method creates a alertbox for handling exeption in RLEopen open method
     * and URLopen methods in GameofLifeController.
     */
    static void filenotFoundError() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("ERROR!");
        alert.setContentText("This is not a valid URL-adress, please try again!");

        alert.showAndWait();
    }
}
