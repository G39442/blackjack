/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model.Data;

import Model.Enums.*;
import java.util.*;

/**
 *
 * @author tanjim
 */
public abstract class Participant {
    
    protected String name;
    protected List<Card> hand;
    protected int points;
    protected int gains;
    protected int number_of_Hands;
    protected int total_number_of_cards;
    
    
    public void takeCard(Card card) throws BlackJackException{
        if (card == null)
            throw new BlackJackException("carte recu est Null");
        hand.add(card);
        total_number_of_cards++;
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
    
    public void changeGains(int gains) {
        this.gains = this.gains+gains;
    }
    
    public void increment_Number_of_Hands() {
        this.number_of_Hands = number_of_Hands + 1;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Card> getHand() {
        return hand;
    }
    
    public int getPoints() {
        return points;
    }
    
    public int getGains() {
        return gains;
    }
    
    public int getNumber_of_Hands() {
        return number_of_Hands;
    }
    
    public void setPoints(int points) throws BlackJackException {
        if (points < 0) throw new BlackJackException("Points ne peut etre negatif");
        this.points = points;
    }
    
    public double getAvg_gains() {
        if (gains == 0 || number_of_Hands == 0) return 0;
        return ((double)gains/(double)number_of_Hands);
    }
    
    public int getTotal_number_of_cards() {
        return total_number_of_cards;
    }
    
    public double getAvg_number_of_cards() {
        if (number_of_Hands == 0 || total_number_of_cards == 0) return 0;
        return ((double)total_number_of_cards/(double)number_of_Hands);
    }
    
    @Override
    public String toString() {
        return "Participant{" + "name=" + name + ", hand=" + hand + ", points=" + points + ", gains=" + gains + ", number_of_Hands=" + number_of_Hands + '}';
    }
}
