/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Model.Data.*;
import Model.Engine.*;
import Views.Outils.*;
import Views.*;
import Views.withSceneBuilder.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author tanjim
 */
public class ControllerFXMLController extends AbstractView implements Initializable {
    
    private Game model;
    Alert alert;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Slider slider;
    
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
    
    @FXML
    private MenuItem viewDealer;
    
    @FXML
    private MenuItem viewPlayer;
    
    @FXML
    private MenuItem viewSummary;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        viewPlayer.setDisable(true);
        viewDealer.setDisable(true);
        viewSummary.setDisable(true);
    }
    
    @FXML
    public void openPlayerView(){
        handleStartButtonAction();
    }
    
    @FXML
    public void history(){
        
        new FindPLayer();
    }
    @FXML
    public void stop_game(){
        System.out.println("stop gama ca marche");
        this.model.addPlayerToDatabase();
        
    }
    
    @FXML
    public void openSummaryView(ActionEvent event) throws IOException{
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/Views/withSceneBuilder/SummaryViewFXML.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene ((Pane)myLoader.load());
        
        stage.setScene(scene);
       
        SummaryViewFXMLController con  = myLoader.<SummaryViewFXMLController>getController();
        
        con.setGameModel(model);
        
        stage.show();
    }
    
    @FXML
    public void openDealerView(){
        if (this.model == null){
            try {
                this.model = new Game(new Deck(),new Human(playerName_textField.getText(),((int)slider.getValue())));
            } catch (BlackJackException ex) {
                System.out.println(ex.getMessage());
            }
        }
        DealerView dealerView = new DealerView(model);
    }
    
    @FXML
    private void handleStartButtonAction() {
        if (this.model == null){
           try {
                this.model = new Game(new Deck(),new Human(playerName_textField.getText(),((int)slider.getValue())));
           } catch (BlackJackException ex) {
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
            model.addListener(this);
            slider.setDisable(true);
            button_start.setDisable(true);
            setDisableButtons(false);
            PlayerView playerView = new PlayerView(model);
            
        }
    }
    
    @FXML
    private void handle_CardButton_Action(ActionEvent event) {
        try {
            model.hit_me();
        } catch (BlackJackException ex) {
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    
    @FXML
    private void stop_button() {
        try {
            button_nouv_manche.setDisable(false);
            button_stop.setDisable(true);
            model.stopRound();
            openDealerView();
        } catch (BlackJackException ex) {
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handle_Nouv_Manche(){
        try {
            model.newHand();
            button_nouv_manche.setDisable(true);
            button_stop.setDisable(false);
        } catch (BlackJackException ex) {
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    
    private void setDisableButtons(boolean bool){
        button_arreter_jeu.setDisable(bool);
        button_card.setDisable(bool);
        button_nouv_manche.setDisable(bool);
        button_stop.setDisable(bool);
        viewDealer.setDisable(bool);
        viewPlayer.setDisable(bool);
        viewSummary.setDisable(bool);
    }
    
    @Override
    public void notifieChangement() {
        if (model != null){
            if(model.isOver()){
                button_stop.setDisable(true);
                button_card.setDisable(true);
                button_nouv_manche.setDisable(false);
            }else{
                button_stop.setDisable(false);
                button_card.setDisable(false);
                button_nouv_manche.setDisable(true);
            }
        }
    }
}