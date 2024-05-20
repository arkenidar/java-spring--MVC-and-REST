package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("message")
public class ChatRoutes {

    @GetMapping("chat-ui.html")
    public String chatUI() {
        return "chat-ui";
    }

    @PostMapping("sending")
    public @ResponseBody String messageSending(@RequestParam String sender, @RequestParam String message_text) {
        jdbc.update("insert into chat_messages (sender, message_text) values (?,?)", sender, message_text);
        return "messageSending() success";
    }

    @GetMapping("listing")
    public String messageListing(Model model) {
        model.addAttribute("messages", jdbc.queryForList("select sender, message_text from chat_messages"));
        return "chat-message-listing";
    }

    private final JdbcTemplate jdbc;

    public ChatRoutes(JdbcTemplate injJdbcTemplate) {
        jdbc = injJdbcTemplate;
    }
}
