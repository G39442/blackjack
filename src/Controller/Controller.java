/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



//https://github.com/AlmasB/blackjack/tree/master/src/com/almasb/blackjack


package Controller;

import javafx.application.*;
import static javafx.application.Application.launch;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 *
 * @author tanjim
 */
public class Controller extends Application {
    
   @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ControllerFXML.fxml"));
        root.setStyle("-fx-background-color : black");
      
        root.getStylesheets().add("style/BlackJackCSS.css");
        Scene scene = new Scene(root);
      
      //  scene.set
       // f.getStylesheets().add("style/BlackJackCSS.css");
       // scene.getStylesheets().add("style/BlackJackCSS.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
