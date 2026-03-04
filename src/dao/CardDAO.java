package dao;

import connection.Connectivity;
import model.CardModel;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {

    public CardDAO() {}

    public boolean add(CardModel card) throws SQLException {
        String sql = "INSERT INTO card (color, type, value, is_active) VALUES (?, ?, ?, ?)";
        Statement stmt = null;
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            ps = connection.prepareStatement(sql);

            ps.setString(1, card.getColor());
            ps.setString(2, card.getType());
            ps.setObject(3, card.getValue());
            ps.setBoolean(4, true);

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

    public List<CardModel> findAll() throws SQLException {
        List<CardModel> cards = new ArrayList<>();
        String sql = "SELECT * FROM card";
        Statement stmt = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CardModel card = new CardModel();
                card.setId(resultSet.getLong("id"));
                card.setColor(resultSet.getString("color"));
                card.setType(resultSet.getString("type"));
                card.setValue((Integer) resultSet.getObject("value"));
                card.setActive(resultSet.getBoolean("is_active"));

                // Dépend du BD qu'on va utiliser
                try {
                    card.setCreatedAt(resultSet.getObject("created_at", OffsetDateTime.class));
                    card.setModifiedAt(resultSet.getObject("modified_at", OffsetDateTime.class));
                } catch (Exception ignore) {}

                cards.add(card);
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

        return cards;
    }

    public CardModel findById(Long id) throws SQLException {
        String sql = "SELECT * FROM card WHERE id = ?";
        Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            stmt = connection.createStatement();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                CardModel card = new CardModel();
                card.setId(rs.getLong("id"));
                card.setColor(rs.getString("color"));
                card.setType(rs.getString("type"));
                card.setValue((Integer) rs.getObject("value"));
                card.setActive(rs.getBoolean("is_active"));

                try {
                    card.setCreatedAt(rs.getObject("created_at", OffsetDateTime.class));
                    card.setModifiedAt(rs.getObject("modified_at", OffsetDateTime.class));
                } catch (Exception ignore) {}

                return card;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (stmt != null) stmt.close();
            if (connection != null && !connection.isClosed()) connection.close();
        }

        return null;
    }

    public boolean update(CardModel card) throws SQLException {
        String sql = "UPDATE card SET color = ?, type = ?, value = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = Connectivity.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, card.getColor());
            preparedStatement.setString(2, card.getType());
            preparedStatement.setObject(3, card.getValue());
            preparedStatement.setLong(4, card.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        return false;
    }

    public boolean delete(Long id) throws SQLException {
        String sql = "UPDATE card SET is_active = ? WHERE id = ?";
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

        } catch (Exception e) {
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