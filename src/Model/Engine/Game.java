/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model.Engine;

import DataBase.*;
import Model.Data.*;
import Views.Outils.*;
import java.util.*;

/**
 *
 * @author tanjim
 */
public class Game {
    private Deck deck;
    private Participant winner;
    private Human player;
    private Dealer bank;
    private List<InterfaceView> views_list;
    private boolean over;
    
    public Game(Deck deck, Human p) throws BlackJackException {
        this.deck = deck;
        this.player = p;
        this.bank = new Dealer();
        this.views_list = new ArrayList<>();
        this.over = false;
        this.winner = null;
        startGame();
    }
    
    private void startGame() throws BlackJackException{
        player.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
        
        bank.takeCard(deck.drawCard());
        bank.takeCard(deck.drawCard());
        bank.getHand().get(1).setHidden(true);
        checkBuche();
    }
    
    public void newHand() throws BlackJackException{
        player.getHand().clear();
        bank.getHand().clear();
        deck.refill();
        player.setPoints(0);
        bank.setPoints(0);
        over = false;
        winner = null;
        startGame();
        fire();
    }
    
    
    public void checkBuche(){
        if(player.getPoints()==21){
            setWinner(player);
            changeGameState();
        }else if(player.getPoints()>21){
            setWinner(bank);
            changeGameState();
        }
    }
    
    private void changeGameState(){
        if (winner != null){
            over = true;
            if(winner == player){
                player.changeGains(player.getBet());
                bank.changeGains(player.getBet()*-1);
                
            }else if(winner == bank){
                player.changeGains(player.getBet()*-1);
                bank.changeGains(player.getBet());
            }
            
            bank.increment_Number_of_Hands();
            player.increment_Number_of_Hands();
            fire();
        }
    }
    
    public void hit_me() throws BlackJackException{
        //if (over)
          //  throw new BlackJackException ("jeu deja terminé over = true");
        
        player.takeCard(deck.drawCard());
        fire();
        checkBuche();
        
    }
    
    
    public void stopRound() throws BlackJackException{
        playDealerRound();
    }
    
    public void playDealerRound() throws BlackJackException{
        //si le joueur a deja gagné ou perdu, on doit desactiver le bouton stop
        
        if (!isOver()){
            while(bank.getPoints() < 21 &&
                    player.getPoints() < 21 &&
                    player.getPoints() > bank.getPoints()){
                
                bank.takeCard(deck.drawCard());
            }
            bank.getHand().get(1).setHidden(false);
            over = true;
            decideWinner();
        }
    }
    
    
    private void decideWinner(){
        if(player.getPoints() == 21 ||
                (player.getPoints() < 21 && bank.getPoints() > 21) ||
                (player.getPoints() < 21 && player.getPoints() > bank.getPoints())){
            
            setWinner(player);
        } else {
            setWinner(bank);
        }
        changeGameState();
        fire();
    }
    
    public void addPlayerToDatabase(){
        
        try {
            recBlackJack r = new recBlackJack(this.player.getName(),null, this.player.getNumber_of_Hands()
                    , this.getPlayer().getGains());
            
            recBlackJackDb.addRecBJ(r);
        } catch (Model.Data.BlackJackException ex) {
            System.out.println("error");
        }
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
        });
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public Participant getWinner() {
        return winner;
    }
    
    public List<InterfaceView> getViews_list() {
        return views_list;
    }
    
    public boolean isOver() {
        return over;
    }
    
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
    public void setWinner(Participant winner) {
        this.winner = winner;
    }
    
    public void setOver(boolean over) {
        this.over = over;
    }
    
    public Human getPlayer() {
        return player;
    }
    
    public Dealer getBank() {
        return bank;
    }
    
}
