/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Model.*;
import View.Joueur.*;
import java.net.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author tanjim
 */
public class ControllerFXMLController implements Initializable {
    
    private Game model;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Slider spinner;
    
    @FXML
    private Button button_stop;
    
    @FXML
    private Button button_start;
    
    @FXML
    private Button button_card;
    
    @FXML
    private Button button_nouv_manche;
    
    @FXML
    private Button button_arreter_jeu;
    
    @FXML
    private TextField playerName_textField;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("spinner value "+spinner.valueProperty());
        this.model = new Game(new Deck(),new Player(playerName_textField.getText(),((int)spinner.getValue())));
        System.out.println(model.getPlayer().getBet());
    }
    public void openPlayerView(){
        //new PlayerView(this.model);
        handleStartButtonAction();
    }
    
    @FXML
    private void handleStartButtonAction() {
        if (playerName_textField.getText().equals("")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You DO have a Name... Don't YOU ? !!! ");
            alert.setContentText("ENTER YOUR NAME!");
            alert.showAndWait();
        }else{
            button_start.setDisable(true);
            setDisableButtons(false);
            //System.out.println(model);
            PlayerView playerView = new PlayerView(model);
        }
    }
    
    @FXML
    private void handle_CardButton_Action(ActionEvent event) {
        model.play();
    }
    @FXML
    private void stop_button() {
        button_stop.setDisable(true);
        model.stopRound();
    }
    
    @FXML
    private void handle_Nouv_Manche(){
        button_stop.setDisable(false);
        model.newHand();
    }
    
    private void setDisableButtons(boolean bool){
        button_arreter_jeu.setDisable(bool);
        button_card.setDisable(bool);
        button_nouv_manche.setDisable(bool);
        button_stop.setDisable(bool);
    }
}
