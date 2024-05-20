package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
class Routes {

    private final UserData userData;

    public Routes(UserData injUserData) {
        userData = injUserData;
    }

    // https://www.baeldung.com/spring-request-param
    // http://localhost:8080/
    // http://localhost:8080/?id=2

    @GetMapping("/")
    public ModelAndView showDataAsHTML(@RequestParam(required = false, defaultValue = "1") Integer id) {
        ModelAndView model = new ModelAndView("template1");
        model.addObject("attribute1", "TEST: id=" + id);
        model.addObject("attribute2", LocalDateTime.now());
        model.addObject("attribute3", userData.getUsers(10));
        return model;
    }

    // curl http://localhost:8080/db
    @GetMapping("/db")
    public @ResponseBody Iterable<User> showDataAsJSON(
            @RequestParam(required = false, defaultValue = "10") int limit) {
        return userData.getUsers(limit);
    }
}
