package dao;

import model.LoginModel;

import java.sql.*;
import java.time.OffsetDateTime;

public class LoginDAO {

    private final Connection connection;

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * add new line into login table
     * @param login instance of class LoginModel
     * @return int 1 if the line was added to the database, 0 otherwise
     * @throws SQLException p
     */
    public boolean add(LoginModel login) throws SQLException {

        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, login.getUsername());
            stmt.setString(2, login.getPassword());

            return stmt.executeUpdate() > 0;

        }
    }

    public LoginModel login(String username, String password) throws SQLException {

        String sql = "SELECT * FROM login WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    LoginModel user = new LoginModel(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getObject("last_logged", OffsetDateTime.class),
                            rs.getObject("created_at", OffsetDateTime.class)
                    );

                    updateLastLogged(user.getId());

                    return user;
                }
            }
        }

        return null;
    }

    /**
     * update last logged-in timestamp
     * @param id the id of line we want to update the last_logged-in date-time
     * @throws SQLException throw exception
     */
    private void updateLastLogged(Long id) throws SQLException {

        String sql = "UPDATE login SET last_logged = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean delete(Long id) throws SQLException {

        String sql = "DELETE FROM login WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}