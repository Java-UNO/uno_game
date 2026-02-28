package connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity {
    public static Connection getConnection(){
        Connection conn = null;
        DataBase db = new DataBase();
        try {
            conn = DriverManager.getConnection(db.getDatabaseUrl(), db.getUsername(), db.getPassword());
            System.out.println("Connexion OK !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
