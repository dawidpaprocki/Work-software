
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
//        try{
//            int num = Integer.parseInt("Mosiądz");
//            // is an integer!
//            System.out.println("jest");
//        } catch (NumberFormatException e) {
//            // not an integer!
//            System.out.println("nie jest");
//        }

    }



}
