/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Views;

import Model.Engine.*;

/**
 *
 * @author tanjim
 */
public class PlayerView extends AbstractGraphicsView{
    
    
    public PlayerView(Game game_model) {
        setGameModel(game_model);
        setup_labels(game_model.getPlayer());
        setUpStage();
        theStage.setTitle("Player View");
    }
    
    @Override
    public void notifieChangement() {
        if (allSetup){
            label_gameState.setText(gameState_Info());
            label_NameScore.setText(game_model.getPlayer().getName() +
                    " = "+game_model.getPlayer().getPoints());
            
            addCardsFromPlayer(game_model.getPlayer().getHand());
        
        }
    }
    
    @Override
    public String gameState_Info() {
        String s = "Vous avez ";
        
        if (game_model.getWinner() == game_model.getPlayer())
            s += "Gagné !!";
        else if (game_model.getWinner()  == game_model.getBank())
            s+= "Perdu";
        else
            s = "Manche en Cours";
        
        return s;
    }
}
