/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Model;

import Model.Data.Deck;

/**
 *
 * @author tanjim
 */
public class testModel {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Card myCard = new Card(Ranks.QUEEN, Suits.HEARTS);
        //System.out.println(myCard);
        
        Deck mySet = new Deck();
        //System.out.println(mySet);
        
        while(!mySet.isEmpty())
            System.out.println("card drawn = " + mySet.drawCard());
        
        System.out.println(mySet);
        mySet.refill();
        System.out.println(mySet.getDealtCards().size());
    }
    
}
