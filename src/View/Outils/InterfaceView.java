package View.Outils;

/**
 *
 * @author tanjim
 */


public interface InterfaceView {
    
    
    /**
     * Permet à la vue de se mettre à jour car le modèle lui notifie le
     * changement (éventuellement par le biais d'un contrôleur).
     */
    public void notifieChangement() ;
}
