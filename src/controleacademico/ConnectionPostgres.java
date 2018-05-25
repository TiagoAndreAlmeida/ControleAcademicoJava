
package controleacademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author tiago
 */
public class ConnectionPostgres {
    //criar instancia da minha classe
    private static ConnectionPostgres instance = null;
    private Connection connection = null;
    //inicia minha conexao
    private ConnectionPostgres (){
        try{
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
            connection = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/controle_academico",
            "postgres", "root");
        } catch (ClassNotFoundException e) {  
            
            System.out.println("O driver expecificado nao foi encontrado.");
        } catch (SQLException e) {
            
            System.out.println("Nao foi possivel"
                    + " conectar ao Banco de Dados.");
            e.printStackTrace();
        }
    }
    //retorna a instancia
    public static ConnectionPostgres getInstance() {
        if(instance == null){
            instance = new ConnectionPostgres();
        }
        
        return instance;
    }
    //retorna a conexao
    public Connection getConnection () {
        return connection;
    }
    
}
