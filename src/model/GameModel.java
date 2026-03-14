package model;

import java.sql.Timestamp;

/**
 *
 * @author fenit
 */
public class GameModel {

    private int id_game;
    private Timestamp date_debut;
    private Timestamp date_fin;
    private String etat;
    private Integer id_gagnant;
    private Integer score;
    private Timestamp modifierle;
    private boolean actif;

    public GameModel() {
    }

    public GameModel(int id_game, Timestamp date_debut, Timestamp date_fin, String etat, Integer id_gagnant, Integer score, Timestamp modifierle, boolean actif) {
        this.id_game = id_game;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.id_gagnant = id_gagnant;
        this.score = score;
        this.modifierle = modifierle;
        this.actif = actif;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public Timestamp getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Timestamp date_debut) {
        this.date_debut = date_debut;
    }

    public Timestamp getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getId_gagnant() {
        return id_gagnant;
    }

    public void setId_gagnant(Integer id_gagnant) {
        this.id_gagnant = id_gagnant;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getModifierle() {
        return modifierle;
    }

    public void setModifierle(Timestamp modifierle) {
        this.modifierle = modifierle;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }    
}