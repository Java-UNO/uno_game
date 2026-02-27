import connection.Connectivity;
import java.sql.Connection;


public class App {
    public static void main(String[] args){
        Connection conn = Connectivity.getConnection();
        System.out.println(conn);
    }
}
