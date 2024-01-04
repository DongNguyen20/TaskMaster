package com.kopw.app.controller;

import com.kopw.app.model.dto.UserRegisterDto;
import com.kopw.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

//    public HomeController(UserService userService){
//        this.userService = userService;
//    }

    @GetMapping("/home")
    public String goHome(Model model, Authentication authentication) {
        String userName = authentication.getName();
        model.addAttribute("userName", userName); // Đưa userName vào model để sử dụng trong trang home
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userRegister", new UserRegisterDto());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean validAccount = userService.isValidAccount(username, password);
        if (validAccount) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String userName = authentication.getName();
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }

    @PostMapping("/register")
    public String doRegisterUserAccount(@ModelAttribute("userRegister") UserRegisterDto userRegister) {
        try {
            userService.createUser(userRegister);
            return "redirect:/login?success"; // Chuyển hướng với thông báo đăng ký thành công
        } catch (Exception e) {
            return "redirect:/login?error"; // Chuyển hướng với thông báo lỗi
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // Đăng xuất người dùng
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
}

