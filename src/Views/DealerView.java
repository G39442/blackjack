/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Views;

import Model.Data.Dealer;
import Model.Engine.Game;

/**
 *
 * @author tanjim
 */
public class DealerView extends AbstractGraphicsView{
    
    public DealerView(Game game_model) {
        setGameModel(game_model);
        setup_labels(game_model.getBank());
        setUpStage();
        theStage.setTitle("Dealer View");
    }
    
    @Override
    public void notifieChangement() {
        if (allSetup){
            label_gameState.setText(gameState_Info());
            //label_gains.setText("Gains : " +game_model.getBank().getGains());
            //label_nbHands_played.setText("Manches : " + game_model.getBank().getNumber_of_Hands());
            Dealer bank = game_model.getBank();
            addCardsFromPlayer(bank.getHand());
            
            if (bank.hasHiddenCard())
                label_NameScore.setText(bank.getName() + " = ? ");
            else
                label_NameScore.setText(bank.getName() + " = "+bank.getPoints());
            
        }
    }
    
    @Override
    public String gameState_Info() {
        String gameStateInfo = "Vous avez ";
        if (game_model.getWinner() == game_model.getPlayer())
            gameStateInfo += "Perdu :( ";
        else if (game_model.getWinner()  == game_model.getBank())
            gameStateInfo+= "Gagné !!! ";
        else
            gameStateInfo = "Manche en Cours";
        
        return gameStateInfo;
    }
}
