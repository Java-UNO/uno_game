import connection.Connectivity;
import java.sql.Connection;
import java.sql.SQLException;

import model.LoginModel;
import dao.LoginDAO;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = Connectivity.getConnection();
        System.out.println(conn);

        // LoginModel loginModel = new LoginModel("supe_user", "super_user");
        LoginDAO loginDAO = new LoginDAO();
        // boolean created = loginDAO.add(loginModel);
        // System.out.println("is created : " + created);

        LoginModel user = loginDAO.login("admin", "admin");

        boolean deleted = loginDAO.delete(user.getId());
        System.out.println("is deleted : " + deleted);
    }
}
