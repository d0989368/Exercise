package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        User foundUser = userRepository.findByName(user.getName());
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loginuser", user);
            return "redirect:manage";
        } else {
            model.addAttribute("msg", "帳號或密碼錯誤");
            return "login";
        }
    }

    @GetMapping("/manage")
    public String mainPage(Model model, HttpSession session) {
        Object loginuser = session.getAttribute("loginuser");

        if (loginuser != null) {
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "manage";
        } else {
            model.addAttribute("msg", "請重新登入");
            return "login";
        }
    }
}
