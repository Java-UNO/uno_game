package model;

import java.time.OffsetDateTime;

public class CardModel {

    private Long id;
    private String color;
    private String type;
    private Integer value;
    private OffsetDateTime createdAt;
    private OffsetDateTime modifiedAt;
    private boolean active;

    public CardModel() {}

    public CardModel(String color, String type, Integer value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    public CardModel(Long id, String color, String type, Integer value,
                     OffsetDateTime createdAt, OffsetDateTime modifiedAt) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.value = value;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() { return id; }

    public String getColor() { return color; }

    public String getType() { return type; }

    public Integer getValue() { return value; }

    public OffsetDateTime getCreatedAt() { return createdAt; }

    public OffsetDateTime getModifiedAt() { return modifiedAt; }

    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }

    public void setColor(String color) { this.color = color; }

    public void setType(String type) { this.type = type; }

    public void setValue(Integer value) { this.value = value; }

    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public void setModifiedAt(OffsetDateTime modifiedAt) { this.modifiedAt = modifiedAt; }

    public void setActive(boolean active) { this.active = active; }

}