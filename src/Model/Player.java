/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model;

import Model.Enums.*;
import java.util.*;

/**
 *
 * @author tanjim
 */
public class Player {
    private String name;
    private List<Card> hand;
    private int points;
    private int bet;
    private int gains;
    private int number_of_Hands;
    private int avgNumber_of_Cards;
    
    public Player(String name,int bet) {
        this.name = name;
        this.points = 0;
        this.bet = bet;
        this.number_of_Hands = 0;
        this.hand = new ArrayList<>();
    }
    
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.bet = 0;
        this.number_of_Hands = 0;
        this.hand = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Card> getHand() {
        return hand;
    }
    
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    
    public void getCard(Card card){
        hand.add(card);
        if ((card.getRank() == Ranks.ACE) && (points + 11 > 21)){
            points += 1;
        }else{
            points += card.getValue();
        }
    }
    
    public void clear(){
        hand.clear();
        points = 0;
    }

    public int getGains() {
        return gains;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void changeGains(int gains) {
        this.gains = this.gains+gains;
    }

    public void increment_Number_of_Hands() {
        this.number_of_Hands = number_of_Hands + 1;
    }

    public void setAvgNumber_of_Cards(int avgNumber_of_Cards) {
        this.avgNumber_of_Cards = avgNumber_of_Cards;
    }
    
    public int getPoints() {
        return points;
    }
    
    public int getBet() {
        return bet;
    }
    
    public int getNumber_of_Hands() {
        return number_of_Hands;
    }
    
    public int getAvgNumber_of_Cards() {
        return avgNumber_of_Cards;
    }
    
    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", gains=" + points + ", bet=" + bet + '}';
    }
    
    
}
