package model;

public class PlayerModel {

    private Long id;
    private String displayName;

    public PlayerModel() {}

    public PlayerModel(String displayName) {
        this.displayName = displayName;
    }


    public PlayerModel(Long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}