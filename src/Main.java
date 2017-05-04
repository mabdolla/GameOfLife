import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    /**
     *Opens the window that contains the Game Of Life.
     *
     * @author Fredrik, Hans Jacob, Mohammad
     *         Studentnr : S309293, s305064, s309856
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Controller/GameOfLife.fxml"));
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root, 1180, 750));
        primaryStage.show();


    }

    /**
     * launches program.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
