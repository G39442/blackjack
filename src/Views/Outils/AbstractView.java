/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Views.Outils;

import Model.Engine.Game;

/**
 *
 * @author tanjim
 */
public abstract class AbstractView implements InterfaceView{
    
    protected Game game_model;
    
    public void setGameModel(Game model){
        this.game_model = model;
        this.game_model.addListener(this);
    }
}
