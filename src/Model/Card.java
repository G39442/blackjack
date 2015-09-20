/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model;

import Model.Enums.Ranks;
import Model.Enums.Suits;

/**
 *
 * @author tanjim
 */
public class Card {
    private final Suits suit;
    private final Ranks rank;
    private final int value;
    
    public Card(Ranks rank,Suits suit){
        this.rank = rank;
        this.suit = suit;
        this.value = rank.value;
    }
    
    public Suits getSuit() {
        return suit;
    }
    
    public Ranks getRank() {
        return rank;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return rank.toString() + " of " + suit + " value = " + value;
    }
    
}
