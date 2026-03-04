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
    
    public boolean update(PlayerModel player) throws SQLException {
    String sql = "UPDATE player SET display_name = ? WHERE id = ?";
    PreparedStatement preparedStatement = null;
    Connection connection = null;

    try {
        connection = Connectivity.getConnection();
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, player.getDisplayName());
        preparedStatement.setLong(2, player.getId());

        return preparedStatement.executeUpdate() > 0;

    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    } finally {
        if(preparedStatement != null) { preparedStatement.close(); }
        if(connection != null) { connection.close(); }
        }

        return false;
    }

    // DELETE 
public int DeletePlayer(PlayerModel player) throws SQLException {

    String sql = "DELETE public.player "
            + "WHERE id=?";

    Connection c = null;
    PreparedStatement ps = null;
    int rows = 0;

    try {
        c = Connectivity.getConnection();
        ps = c.prepareStatement(sql);

        ps.setLong(1, player.getId());

        rows = ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Erreur = " + e.getMessage());
    } finally {
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }
    return rows;
    }
}