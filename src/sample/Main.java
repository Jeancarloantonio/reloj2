/* ARANDA CEN JEANCARLO ANTONIO*/
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //mandamos a llamar a la skin que se creo en scenebuilder
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //le damos un titulo a la ventana
        primaryStage.setTitle("RELOJ");
        //le asignamos el panel principal de la skin al stage (ventana)
        primaryStage.setScene(new Scene(root));

        //mostramos la ventana
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
