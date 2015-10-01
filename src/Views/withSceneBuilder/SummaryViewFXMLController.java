/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Views.withSceneBuilder;

import Model.Data.Human;
import Views.Outils.AbstractView;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author tanjim
 */
public class SummaryViewFXMLController extends AbstractView implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label label_avgNbCard;
    
    @FXML
    private Label label_namePlayer;
    
    @FXML
    private Label label_nbHands;
    
    @FXML
    private Label label_gains;
    
    @FXML
    private Label label_avgGains;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @Override
    public void notifieChangement() {
        DecimalFormat formatNumber = new DecimalFormat(".##");
        Human player = game_model.getPlayer();
        this.label_namePlayer.setText(player.getName());
        this.label_gains.setText(""+player.getGains());
        this.label_nbHands.setText(""+player.getNumber_of_Hands());
        this.label_avgGains.setText(formatNumber.format(player.getAvg_gains()));
        this.label_avgNbCard.setText(formatNumber.format(player.getAvg_number_of_cards()));
    }
}
