package model;

public class PlayerModel {

    private Long id;
    private String displayName;
    private boolean hasSaidUno;

    public PlayerModel() {}

    public PlayerModel(String displayName, boolean hasSaidUno) {
        this.displayName = displayName;
        this.hasSaidUno = hasSaidUno;
    }

    public PlayerModel(Long id, String displayName, boolean hasSaidUno) {
        this.id = id;
        this.displayName = displayName;
        this.hasSaidUno = hasSaidUno;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isHasSaidUno() {
        return hasSaidUno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setHasSaidUno(boolean hasSaidUno) {
        this.hasSaidUno = hasSaidUno;
    }
}