/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package View.Joueur;

import Model.*;
import View.Outils.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 *
 * @author tanjim
 */
public class PlayerView extends AbstractView{
    
    Stage playerViewStage;
    StackPane root;
    Scene scene;
    Label label_playerName;
    Label label_gameState;
    Label label_scorePlayer,label_scoreDealer;
    Label label_gainsPlayer;
    Label label_games_won;
    Label label_nbHands_played;
    
    VBox Vbox_for_boxes;
    HBox HBOX_labels;
    HBox HBOX_PlayerImages;
    HBox HBOX_DealerImages;
    
    String imageName;
    boolean allSetup = false;
    
    
    public PlayerView(Game model){
        setGameModel(model);
        setUpStage();
    }
    
    public void setUpStage() {
        imageName = "/images/";
        playerViewStage = new Stage();
        root = new StackPane();
        
        Vbox_for_boxes = new VBox();
        HBOX_labels = new HBox();
        HBOX_PlayerImages = new HBox();
        HBOX_DealerImages = new HBox();
        
        label_scorePlayer = new Label(" = "+game_model.getPlayer().getPoints());
        label_scoreDealer = new Label(" dealer = "+game_model.getDealer().getPoints());
        label_gameState = new Label("Manche en cours");
        label_playerName = new Label(game_model.getPlayer().getName());
        label_gainsPlayer = new Label("Gains : " +game_model.getPlayer().getGains());
        label_nbHands_played = new Label("Manches : "+ game_model.getPlayer().getNumber_of_Hands());
        
        HBOX_labels.setSpacing(50);
        HBOX_PlayerImages.setSpacing(5);
        HBOX_DealerImages.setSpacing(5);
        Vbox_for_boxes.setSpacing(25);
        
        setLabelText_size_color(new Font("Arial",20), Color.WHITE);
        
        Vbox_for_boxes.getChildren().addAll(label_gainsPlayer,label_nbHands_played,HBOX_labels,HBOX_PlayerImages,HBOX_DealerImages);
        HBOX_labels.getChildren().addAll(label_playerName,label_scorePlayer,label_scoreDealer,label_gameState);
        
        
        addCardImage();
        
        //root.getChildren().addAll(HBOX_labels,HBOX_PlayerImages);
        String image = "/images/background.jpg";
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: repeat;");
        root.getChildren().addAll(Vbox_for_boxes);
        root.setPrefHeight(400);
        root.setPrefWidth(550);
        scene = new Scene(root);
        playerViewStage.setTitle(label_playerName.getText() + " View");
        playerViewStage.setScene(scene);
        
        playerViewStage.show();
        allSetup = true;
        notifieChangement();
    }
    
    @Override
    public void notifieChangement() {
        if (allSetup){
            System.out.println("Winner : " + game_model.getWinnerName());
            if (game_model.isOver()){
                if (game_model.getWinnerName().equals(game_model.getDealer().getName())){
                    label_gameState.setText("Vous Avez Perdu");
                }  else{
                    label_gameState.setText("Vous Avez Gagné !! ");
                }
                label_gainsPlayer.setText("Gains : "+game_model.getPlayer().getGains());
                label_nbHands_played.setText("Manches : " + game_model.getPlayer().getNumber_of_Hands());
            }else {
                label_gameState.setText("Manche en cours");
            }
            addCardImage();
            label_scorePlayer.setText(" = "+game_model.getPlayer().getPoints());
            label_scoreDealer.setText(" dealer = "+game_model.getDealer().getPoints());
        }
    }
    
    public void addCardImage(){
        List<Card> playersCards = game_model.getPlayer().getHand();
        List<Card> dealersCards = game_model.getDealer().getHand();
        
        HBOX_PlayerImages.getChildren().removeAll(HBOX_PlayerImages.getChildren());
        HBOX_DealerImages.getChildren().removeAll(HBOX_DealerImages.getChildren());
        
        addCardsFromPlayer(playersCards,game_model.getPlayer().getName());
        addCardsFromPlayer(dealersCards,game_model.getDealer().getName());
        
        /*               if (playersCards.size() > 2){
        Card c = playersCards.get(playersCards.size()-1);
        imageName += c.getSuit().toString()+"/"+c.getRank().toString()+".png";
        HBOX_PlayerImages.getChildren().add(new ImageView(imageName));
        }else{
        for (Card c : playersCards ){
        imageName += c.getSuit().toString()+"/"+c.getRank().toString()+".png";
        HBOX_PlayerImages.getChildren().add(new ImageView(imageName));
        imageName = "/images/";
        }
        }
        //playerViewStage.setWidth(playerViewStage.getWidth() + 50);
        */  // imageName = "/images/";
    }
    private void addCardsFromPlayer(List<Card> playersCards, String playerName){
        for (Card c : playersCards ){
            imageName += c.getSuit()+"/"+c.getRank()+".png";
            if(playerName.equals(game_model.getPlayer().getName()))
                HBOX_PlayerImages.getChildren().add(new ImageView(imageName));
            
            if(playerName.equals(game_model.getDealer().getName()))
                HBOX_DealerImages.getChildren().add(new ImageView(imageName));
            
            imageName = "/images/";
        }
    }
    
    private void setLabelText_size_color(Font f,Color c){
        label_playerName.setFont(f);
        label_scorePlayer.setFont(f);
        label_scoreDealer.setFont(f);
        label_gameState.setFont(f);
        label_gainsPlayer.setFont(f);
        label_nbHands_played.setFont(f);
        
        label_playerName.setTextFill(c);
        label_scorePlayer.setTextFill(c);
        label_scoreDealer.setTextFill(c);
        label_gameState.setTextFill(c);
        label_gainsPlayer.setTextFill(c);
        label_nbHands_played.setTextFill(c);
        
    }
}