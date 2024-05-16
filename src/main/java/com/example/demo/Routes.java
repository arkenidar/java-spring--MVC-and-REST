package com.example.demo;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
class Routes {

    private final JdbcTemplate jdbc;

    public Routes(JdbcTemplate injJdbcTemplate) {
        jdbc = injJdbcTemplate;
    }

    // https://www.baeldung.com/spring-request-param
    // http://localhost:8080/
    // http://localhost:8080/?id=2

    @GetMapping("/")
    public ModelAndView showDataAsHTML(@RequestParam(required = false, defaultValue = "1") Integer id) {
        ModelAndView model = new ModelAndView("template1");
        model.addObject("attribute1", "TEST: id=" + id);
        model.addObject("attribute2", LocalDateTime.now());
        return model;
    }

    // curl http://localhost:8080/db
    // curl http://localhost:8080/db?username=yalf
    // {"name":"dario","username":"yalf"}
    @GetMapping("/db")
    public @ResponseBody Iterable<User> showDataAsJSON(
            @RequestParam(required = false, defaultValue = "(no username)") String username) {
        return jdbc.query("select 'dario' as name, ? as username",

                (ResultSet result, int rowNum) -> {
                    return new User(result.getString("name"), result.getString("username"));
                },

                username);
    }
}

class User {

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
