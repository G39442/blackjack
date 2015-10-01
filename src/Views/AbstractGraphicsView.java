/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Views;

import Model.Data.*;
import Views.Outils.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 *
 * @author tanjim
 */
public abstract class AbstractGraphicsView extends AbstractView{
    
    Stage theStage;
    AnchorPane root;
    Scene scene;
    Label label_NameScore;
    Label label_gameState;
    HBox HBOX_labels;
    HBox HBOX_Images;
    String imageName;
    boolean allSetup = false;
    
    protected void setUpStage() {
        
        imageName = "/images/";
        theStage = new Stage();
        
        root = new AnchorPane();
        
        HBOX_labels = new HBox();
        HBOX_Images = new HBox();
        
        HBOX_labels.setSpacing(250);
        HBOX_Images.setSpacing(5);
        
        setLabelText_size_color(new Font("Arial",20), Color.WHITE);
        
        HBOX_labels.getChildren().addAll(label_NameScore,label_gameState);
        
        String image = "/images/b.jpg";
        root.setStyle("-fx-background-image: url('"+image+"'); " +
                      "-fx-background-position: center center; " +
                      "-fx-background-repeat: repeat;");
        
        AnchorPane.setTopAnchor(HBOX_labels, 100.);
        AnchorPane.setLeftAnchor(HBOX_labels, 100.);
        AnchorPane.setTopAnchor(HBOX_Images, 200.);
        AnchorPane.setLeftAnchor(HBOX_Images, 100.);
        root.getChildren().addAll(HBOX_labels,HBOX_Images);
        root.setPrefHeight(430);
        root.setPrefWidth(740);
        
        scene = new Scene(root);
        
        theStage.setScene(scene);
        theStage.setMaxWidth(740);
        theStage.setMaxHeight(430);
        theStage.show();
        allSetup = true;
        notifieChangement();
    }
    
    
    abstract String gameState_Info();
    
    
    
    protected void addCardsFromPlayer(List<Card> playersCards){
        HBOX_Images.getChildren().removeAll(HBOX_Images.getChildren());
        
        for (Card c : playersCards ){
            if(c.isHidden()) imageName +="hidden.png";
            else  imageName += c.getSuit()+"/"+c.getRank()+".png";
            
            HBOX_Images.getChildren().add(new ImageView(imageName));
            imageName = "/images/";
        }
    }
    
    private void setLabelText_size_color(Font f,Color c){
        label_NameScore.setFont(f);
        label_gameState.setFont(f);
        label_NameScore.setTextFill(c);
        label_gameState.setTextFill(c);
        
    }
    
    protected void setup_labels(Participant pariticipant){
        label_gameState = new Label("Manche en cours");
        label_NameScore = new Label(pariticipant.getName() + "  = "+
                                    pariticipant.getPoints());
        
    }
}
