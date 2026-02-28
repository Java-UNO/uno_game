package dao;

import connection.Connectivity;
import model.LoginModel;
import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class LoginDAO {


    public LoginDAO() {}

    public boolean add(LoginModel login) throws SQLException {
        String sql = "INSERT INTO login (username, password, is_active) VALUES (?, ?, ?)";
        Statement stmt = null;
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            ps = connection.prepareStatement(sql);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            ps.setBoolean(3, true);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) ps.close();
            if (stmt != null) stmt.close();
            if (connection != null && !connection.isClosed()) connection.close();
        }
    }

    public LoginModel login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                LoginModel user = new LoginModel(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getObject("last_logged", OffsetDateTime.class),
                        resultSet.getObject("created_at", OffsetDateTime.class)
                );
                return user;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (stmt != null) stmt.close();
            if (connection != null && !connection.isClosed()) connection.close();
        }

        return null;
    }

    public List<LoginModel> findAll() throws SQLException {
        String sql = "SELECT * FROM login";
        Statement stmt = null;
        PreparedStatement preparedStatement  = null;
        ResultSet resultSet = null;
        Connection connection = null;

        List<LoginModel> result = new ArrayList<>();
        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("uername");
                boolean active = resultSet.getBoolean("is_active");
                LoginModel user = new LoginModel();
                user.setId(id);
                user.setUsername(username);
                user.setActive(active);
                result.add(user);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (stmt != null) stmt.close();
            if (connection != null && !connection.isClosed()) connection.close();
        }
        return  result;
    }


    public boolean delete(Long id) throws SQLException {
        String sql = "update login SET is_active = ? WHERE id = ?";
        Statement stmt = null;
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setLong(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
            if (stmt != null) stmt.close();
            if (connection != null && !connection.isClosed()) connection.close();
        }
        return false;
    }

}