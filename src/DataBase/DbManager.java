// Error reading included file Templates/Classes/Templates/Licenses/license-default.txt
package DataBase;

import Model.Data.*;



public class DbManager {
        
    private static final java.lang.String url = "jdbc:derby://localhost:1527/BlackJackDatabase";
    private static final java.lang.String user = "app";
    private static final java.lang.String password = "app";
    private static java.sql.Connection connection;

    
    /**
     * Mémorise la connexion passée en paramètre
     * @param con
     */
    public static void setConnection(java.sql.Connection con) {
        connection = con;
    }
    
    /**
     * Retourne la connexion établie ou à défaut, l'établit
     * @return
     * @throws BlackJackException
     */
    public static java.sql.Connection getConnection() throws BlackJackException {
        if(connection==null){
            try {
                try {
                    java.lang.Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (java.lang.ClassNotFoundException ex) {
                    java.lang.System.err.println("driver non trouvé");
                }
                connection = java.sql.DriverManager.getConnection(url, user, password);
            } catch (java.sql.SQLException ex) {
                java.lang.System.err.println("Erreur établissement connexion : " + ex.getMessage());
            }
        }
        return connection;
    }
    
    /**
     * débute une transaction
     * @throws BlackJackException
     */
    public static void startTransaction() throws BlackJackException {
        try {
            getConnection().setAutoCommit(false);
        } catch (java.sql.SQLException ex) {
            throw new Model.Data.BlackJackException("Impossible de démarrer une transaction");
        }
    }
    
    /**
     * débute une transaction en spécifiant son niveau d'isolation
     * Attention, ceci n'est pas implémenté sous Access!
     * (cette notion sera abordée ultérieurement dans le cours de bd)
     * @param isolationLevel
     * @throws BlackJackException
     */
    public static void startTransaction(int isolationLevel) throws BlackJackException {
        try {
            getConnection().setAutoCommit(false);
            
            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new BlackJackException("Degré d'isolation inexistant!");
            }
            
            
            getConnection().setTransactionIsolation(isol);
        } catch (java.sql.SQLException ex) {
            throw new BlackJackException("Impossible de démarrer une transaction");
        }
    }
    
    /**
     * valide la transaction courante
     * @throws BlackJackException
     */
    public static void valideTransaction() throws BlackJackException{
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (java.sql.SQLException ex) {
            throw new BlackJackException("Impossible de valider la transaction");
        }
    }
    
    /**
     * annule la transaction courante
     * @throws BlackJackException
     */
    public static void annuleTransaction() throws BlackJackException {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (java.sql.SQLException ex) {
            throw new BlackJackException("Impossible d'annuler la transaction");
        }
    }
}
