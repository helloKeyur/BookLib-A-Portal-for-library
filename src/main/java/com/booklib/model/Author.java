package com.booklib.model;

import java.sql.Timestamp;

public class Author {
    private int id;
    private String name;
    private String bio;
    private String email;
    private String profile;
    private Timestamp createdAt;
    
    public Author(){};
    
    public Author(String name, String bio, String email, String profile){
        super();
        this.name = name;
        this.bio = bio;
        this.email = email;
        this.profile = profile;
    }
    
    public Author(int id, String name, String bio, String email, String profile){
        this(name,bio,email,profile);
        this.id = id;
    }
    
    public Author(int id, String name, String bio, String email, String profile, Timestamp createdAt){
        this(id,name,bio,email,profile);
        this.createdAt = createdAt;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
