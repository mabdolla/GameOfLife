import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    /**
     \\* @param primaryStage
     \\* @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controller/GameOfLife.fxml"));
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(new Scene(root, 1000, 640));
        primaryStage.show();


    }


    /**
     * @param
     */
    public static void main(String[] args) {
        launch(args);
    }
}
