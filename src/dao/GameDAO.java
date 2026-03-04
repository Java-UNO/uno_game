package com.mycompany.poojdbc.dao;

import com.mycompany.poojdbc.Connectivity;
import com.mycompany.poojdbc.model.GameModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fenit
 */
public class GameDAO {

    // CREATE 
    public int CreateGame (GameModel game) throws SQLException{
        String sql = "INSERT INTO public.game(\n" +
        "date_debut, date_fin, etat, id_gagnant, score, modifierle, actif)\n" +
        "VALUES (CURRENT_TIMESTAMP, NULL, ?, ?, ?, NULL, ?)";
        
        Connection c = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try{
            c = Connectivity.getConnection();
            ps = c.prepareStatement(sql);
            
            ps.setString(1, game.getEtat());
            ps.setObject(2, game.getId_gagnant());
            ps.setObject(3, game.getScore());
            ps.setBoolean(4, game.isActif());
            
            rows = ps.executeUpdate();
        } catch (Exception e){
            System.out.println("Erreur = " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (c != null)
                c.close();
        }
        return rows;
    }
    
    // READ
    public static List<GameModel> findAll() throws Exception{
        List<GameModel> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM public.game";
        
        try {
            c = Connectivity.getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                GameModel lm = new GameModel();
                lm.setId_game(rs.getInt(1));
                lm.setDate_debut(rs.getTimestamp(2));
                lm.setDate_fin(rs.getTimestamp(3));
                lm.setEtat(rs.getString(4));
                lm.setId_gagnant(rs.getInt(5));
                lm.setScore(rs.getInt(6));
                lm.setModifierle(rs.getTimestamp(7));
                lm.setActif(rs.getBoolean(8));
                result.add(lm);
                }     
        } catch (Exception e){
                throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (c != null)
                c.close();
        }
        return result;
    }
    
    // UPDATE 
    public int UpdateGame (GameModel game) throws SQLException{
        String sql = "UPDATE public.game "
	+ "SET date_fin=?, etat=?, id_gagnant=?, score=?, modifierle=CURRENT_TIMESTAMP, actif=? "
	+ "WHERE id_game=?";
        
        Connection c = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            c = Connectivity.getConnection();
            ps = c.prepareStatement(sql);
            
            ps.setTimestamp(1, game.getDate_fin());
            ps.setString(2, game.getEtat());
            ps.setObject(3, game.getId_gagnant());
            ps.setObject(4, game.getScore());
            ps.setBoolean(5, game.isActif()); 
            ps.setInt(6, game.getId_game());
            
            return ps.executeUpdate();
            
        } catch (Exception e){
            System.out.println("Erreur = " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (c != null)
                c.close();
        }
        return rows;
    }
    
    //DELETE
    public int DeleteGame (GameModel game) throws SQLException{
        String sql = "UPDATE public.game "
	+ "SET modifierle=CURRENT_TIMESTAMP, actif=false "
	+ "WHERE id_game=?";
        
        Connection c = null;
        PreparedStatement ps = null;
        int rows = 0;
        
        try {
            c = Connectivity.getConnection();
            ps = c.prepareStatement(sql);
            
            ps.setInt(1, game.getId_game());
            
            return ps.executeUpdate();
            
        } catch (Exception e){
            System.out.println("Erreur = " + e.getMessage());
        } finally {
            if (ps != null)
                ps.close();
            if (c != null)
                c.close();
        }
        return rows;
    }
}