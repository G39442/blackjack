/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model.Data;

import java.util.*;

/**
 *
 * @author tanjim
 */
public class Human extends Participant{
    private int bet;
    private int avgNumber_of_Cards;
    
    public Human(String name,int bet) {
        this.name = name;
        this.bet = bet;
       
        this.hand = new ArrayList<>();
    }
    
    public int getBet() {
        return bet;
    }
    
    public int getAvgNumber_of_Cards() {
        return avgNumber_of_Cards;
    }
    
    public void setBet(int bet) {
        this.bet = bet;
    }
    
    public void setAvgNumber_of_Cards(int avgNumber_of_Cards) {
        this.avgNumber_of_Cards = avgNumber_of_Cards;
    }
    
    
    @Override
    public String toString() {
        String s = super.toString();
        s += " bet=" + bet + ", avgNumber_of_Cards=" + avgNumber_of_Cards + '}';
        return s;
    }
    
    
}
