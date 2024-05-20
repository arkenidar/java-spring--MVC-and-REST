package com.example.demo;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

class User {

    public static RowMapper<User> getRowMapper() {
        return (ResultSet result, int rowNum) -> new User(
                result.getString("name"),
                result.getString("username"));
    }

    private String name, username;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
