package model;

import java.time.OffsetDateTime;

public class LoginModel {

    private Long id;
    private String username;
    private String password;
    private OffsetDateTime lastLogged;
    private OffsetDateTime createdAt;
    private boolean active;

    public LoginModel() {}

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginModel(Long id, String username, String password,
                      OffsetDateTime lastLogged, OffsetDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastLogged = lastLogged;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public OffsetDateTime getLastLogged() { return lastLogged; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setLastLogged(OffsetDateTime lastLogged) { this.lastLogged = lastLogged; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}