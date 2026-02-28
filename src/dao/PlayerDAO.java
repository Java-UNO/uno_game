package dao;

import model.PlayerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connection.Connectivity;

public class PlayerDAO {

    public PlayerDAO() {}

    public boolean add(PlayerModel player) throws SQLException {
        String sql = "INSERT INTO player (display_name) VALUES (?)";
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, player.getDisplayName());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {preparedStatement.close();}
            if(stmt != null) {stmt.close();}
            if(connection != null) {connection.close();}
        }
        return false;
    }

    public List<PlayerModel> findAll() throws SQLException {
        List<PlayerModel> players = new ArrayList<>();
        String sql = "SELECT * FROM player";
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try  {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                PlayerModel player = new PlayerModel();
                player.setId(resultSet.getLong("id"));
                player.setDisplayName(resultSet.getString("display_name"));
                players.add(player);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  players;
    }
}