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
public class Dealer extends Participant{
    
    public Dealer() {
        this.name = "Dealer";
        this.gains = 0;
        this.number_of_Hands = 0;
        this.points = 0;
        this.hand = new ArrayList<>();
    }
   
    public boolean hasHiddenCard(){
        if (hand.size() <1)
            return false;
        return this.hand.get(1).isHidden();
    }
}
