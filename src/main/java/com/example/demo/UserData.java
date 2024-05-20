package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserData {

    private final JdbcTemplate jdbc;

    public UserData(JdbcTemplate injJdbcTemplate) {
        jdbc = injJdbcTemplate;
    }

    public Iterable<User> getUsers(int limit) {
        String sql = "select name, username FROM user LIMIT ?";
        return jdbc.query(sql, User.getRowMapper(), limit);
    }
}
