// Error reading included file Templates/Classes/Templates/Licenses/license-default.txt
package DataBase;

import Model.Data.BlackJackException;
import java.sql.*;
import java.time.*;
import java.util.*;

/**
 *
 * @author El Fari Mohamed G39442
 */


public class recBlackJackDb {
    
    
    public static void addRecBJ(recBlackJack recBj) throws BlackJackException {
        
        try {
            
            String query = "INSERT INTO BlackJackData(nom,laDate,nParties,sommeGains) "
                    + " VALUES(?, ?, ?, ?)";
            
            java.sql.Connection connexion = DbManager.getConnection();
            java.sql.PreparedStatement insert = connexion.prepareStatement(query);
            java.util.Date d = new java.util.Date();
            insert.setString(1, recBj.getLeNom());
            insert.setDate(2, new java.sql.Date(d.getTime()));
            insert.setInt(3, recBj.getNParties());
            insert.setInt(4, recBj.getSGains());
            
            
            insert.execute();
            
            
        } catch (SQLException ex) {
            throw new BlackJackException("Création d'édition impossible:\r SQLException: " + ex.getMessage());
        }
    }
    
    public static List<recBlackJack> getPlayer(String s,LocalDate minD,LocalDate maxD){
        recBlackJack rec;
        List<recBlackJack> al= null;
        try {
            al = new java.util.ArrayList<>();
            PreparedStatement stmt;
            
            Connection connexion = DbManager.getConnection();
            String minDate = minD.toString();
            String maxDate = maxD.toString();
            System.out.println("min  "+minDate.toString());
            String query = "SELECT nom, laDate,nParties,sommeGains  "
                    + "FROM BlackJackData where nom like ? "
                    + " And laDate  BETWEEN '"+minDate+"' AND '"+maxDate+"'";
            stmt = connexion.prepareStatement(query);
            stmt.setString(1, "%" + s + "%");
            ResultSet result = stmt.executeQuery();
            
            
            while (result.next()){
                rec =  new DataBase.recBlackJack(result.getString("nom"),result.getDate("laDate"),
                        result.getInt("nParties"),result.getInt("sommeGains"));
                
                
                al.add(rec);
                System.out.println("while");
                
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return al;
        
        
    }
}
