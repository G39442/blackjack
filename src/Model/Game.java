/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model;

import View.Outils.*;
import java.util.*;

/**
 *
 * @author tanjim
 */
public class Game {
    private Deck deck;
    private Player player,dealer;
    private List<InterfaceView> views_list;
    private boolean finished;
    private String winnerName;
    
    public Game(Deck deck, Player player) {
        this.deck = deck;
        this.player = player;
        this.dealer = new Player("Dealer");
        views_list = new ArrayList<>();
        finished = false;
        winnerName = "";
        startGame();
    }
    
    public void newHand(){
        player.getHand().clear();
        dealer.getHand().clear();
        deck.setTheDeck(Deck.fillDeck());
        player.setPoints(0);
        dealer.setPoints(0);
        finished= false;
        winnerName = "";
        startGame();
        fire();
    }
    
    private void startGame(){
        player.getCard(deck.drawCard());
        player.getCard(deck.drawCard());
        checkBuche();
    }
    
    public void play(){
        System.out.println("finished : " + finished);
        System.out.println("winner = " + winnerName);
        if(finished == false){
            player.getCard(deck.drawCard());
            fire();
            checkBuche();
        }
    }
    
    
    public void stopRound(){
        playDealerRound();
    }
    
    public void playDealerRound(){
        //si le joueur a deja gagné ou perdu, on doit desactiver le bouton stop
        if (!isOver()){
            dealer.getCard(deck.drawCard());
            dealer.getCard(deck.drawCard());
            while(dealer.getPoints() < 21 && player.getPoints() < 21 &&
                    player.getPoints() > dealer.getPoints()){
                
                dealer.getCard(deck.drawCard());
            }
            finished = true;
            checkWinner();
        }
    }
    
    
    public void checkBuche(){
        if(player.getPoints()==21){
            winnerName=player.getName();
            finished= true;
            player.changeGains(player.getBet());
            player.increment_Number_of_Hands();
            fire();
        }else if(player.getPoints()>21){
            winnerName=dealer.getName();
            finished= true;
            player.increment_Number_of_Hands();
            player.changeGains(player.getBet()*-1);
            fire();
        }
    }
    
    
    public void checkWinner(){
        if(player.getPoints()==21 ||
                (player.getPoints() < 21 && dealer.getPoints() > 21) ||
                (player.getPoints() < 21 && player.getPoints() > dealer.getPoints())){
            
            winnerName = player.getName();
            finished = true;
            player.changeGains(player.getBet());
            player.increment_Number_of_Hands();
            fire();
        } else {
            winnerName = dealer.getName();
            finished = true;
            player.increment_Number_of_Hands();
            player.changeGains(player.getBet()*-1);
            fire();
        }
        System.out.println("player gains : " + player.getGains());
        System.out.println("Players points " + player.getPoints());
        System.out.println("Dealers points " + dealer.getPoints());
        System.out.println("Dealers cards : " + dealer.getHand());
        /*  if (dealer.getGains() == 21 ||
        player.getGains() > 21 ||
        dealer.getGains() == player.getGains()){
        
        //||(dealer.getGains() < 21 && dealer.getGains() > player.getGains())) {
        finished = true;
        winnerName = "DEALER";
        fire();
        }else if (player.getGains() == 21 ||
        dealer.getGains() > 21){
        //||player.getGains() > dealer.getGains()) {
        finished = true;
        winnerName = player.getName();
        fire();
        }*/
        
    }
    
    public void addListener(InterfaceView vue) {
        views_list.add(vue);
        fire() ;
    }
    
    public void removeListener(InterfaceView vue) {
        views_list.remove(vue);
        fire() ;
    }
    
    
    private void fire() {
        views_list.stream().forEach((vue) -> {
            vue.notifieChangement();
            //System.out.println("notified");
        });
    }
    
    public boolean isOver(){
        return finished;
    }
    
    public void setOver(){
        this.finished = true;
    }
    
    @Override
    //temp method for checking
    public String toString() {
        return deck + ", player=" + player + ", dealer=" + dealer +  ", finished=" + finished + ", winnerName=" + winnerName + '}';
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Player getDealer() {
        return dealer;
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
}
