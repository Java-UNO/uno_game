package dao;

import model.PlayerModel;

import java.sql.*;

public class PlayerDAO {

    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean save(PlayerModel player) throws SQLException {
        String sql = "INSERT INTO player (display_name) VALUES (?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, player.getDisplayName());
        int insert = stmt.executeUpdate();
        return insert > 0;
    }

    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM player WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}