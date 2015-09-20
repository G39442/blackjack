/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model;

import Model.Enums.Ranks;
import Model.Enums.Suits;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tanjim
 */
public class Deck {
    private ArrayList<Card> theDeck;
    private ArrayList<Card> dealtCards;
    
    
    
    public Deck() {
        this.theDeck = fillDeck();
        this.dealtCards = new ArrayList<>();
    }
    
    public Deck(ArrayList<Card> theDeck) {
        this.theDeck = theDeck;
    }
    
    public Card drawCard(){
        int i = (int) (Math.random() * theDeck.size()-1);
        //System.out.println("i = " + i);
        dealtCards.add(theDeck.get(i));
        theDeck.remove(i);
        
        return dealtCards.get(dealtCards.size()-1);
    }
    
    public static ArrayList<Card> fillDeck(){
        ArrayList <Card> tempDeck = new ArrayList<>();
        for(Suits suit : Suits.values()){
            for(Ranks rank : Ranks.values())
                tempDeck.add(new Card(rank, suit));
        }
        Collections.shuffle(tempDeck);
        return tempDeck;
    }
    
    public boolean isEmpty(){
        return theDeck.isEmpty();
    }
    
    public ArrayList<Card> getTheDeck() {
        return theDeck;
    }
    
    public void setTheDeck(ArrayList<Card> theDeck) {
        this.theDeck = theDeck;
    }
    
    public ArrayList<Card> getDealtCards() {
        return dealtCards;
    }
    
    public void setDealtCards(ArrayList<Card> dealtCards) {
        this.dealtCards = dealtCards;
    }
    
    @Override
    public String toString() {
        String s = "";
        for (Card c : theDeck)
            s += c + "\n";
        
        s += theDeck.size();
        return s;
    }
}
