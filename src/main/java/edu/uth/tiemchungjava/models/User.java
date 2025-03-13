package edu.uth.tiemchungjava.models;
import jakarta.persistence.*;
@Entity
@Table (name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// -2^63

    private String username;
    private String password;
    //getter setter


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    //------------------//
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }
    public User() {

    }
}
