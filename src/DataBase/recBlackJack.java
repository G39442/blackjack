// Error reading included file Templates/Classes/Templates/Licenses/license-default.txt
package DataBase;

import java.util.*;

/**
 *
 * @author El Fari Mohamed G39442
 */


public class recBlackJack {
    
    private String leNom;
    private Date laDate;
    private int sGains;
    private int nParties;
    
    public recBlackJack() {
    }
    
    public recBlackJack(java.lang.String leNom, java.util.Date laDate,
                                                int sGains,int nParties) {
        this.leNom = leNom;
        this.laDate = laDate;
        this.sGains = sGains;
        this.nParties = nParties;
        
        
    }

    public void setsGains(int sGains) {
        this.sGains = sGains;
    }

    public void setnParties(int nParties) {
        this.nParties = nParties;
    }
    
    public int getSGains(){
        return sGains;
    }
    
    public java.lang.String getLeNom() {
        return leNom;
        
    }
    
    public java.util.Date getLaDate() {
        return laDate;
    }
    
    public void setLeNom(java.lang.String leNom) {
        this.leNom = leNom;
    }
    
    public void setLaDate(java.util.Date laDate) {
        this.laDate = laDate;
    }
    
    public int getNParties() {
        return nParties;
    }

}