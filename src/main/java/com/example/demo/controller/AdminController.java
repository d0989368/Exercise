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
	
	@PostMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		
		if(StringUtils.hasText(user.getName()) && "666".equals(user.getPassword())) {
			session.setAttribute("loginuser", user);
			return "redirect:/manage";
		}else {
			model.addAttribute("msg","帳號錯誤");
			return "login";
		}
	}
	
	@GetMapping("/manage")
    public String mainPage(Model model, HttpSession session) {
        Object loginuser = session.getAttribute("loginuser");

        if (loginuser != null) {
            // 初始化一些用戶數據
            if (userRepository.count() == 0) {
                userRepository.save(new User(null, "王建民", "111111", 20, "wang@abc.com"));
                userRepository.save(new User(null, "張秉承", "222222", 30, "chang@abc.com"));
                userRepository.save(new User(null, "李小龍", "333333", 22, "lee@abc.com"));
                userRepository.save(new User(null, "陳曉明", "444444", 46, "cheng@abc.com"));
                userRepository.save(new User(null, "張郎", "555555", 52, "chang@abc.com"));
            }

            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "manage";
        } else {
            model.addAttribute("msg", "請重新登入");
            return "login";
        }
    }
}
