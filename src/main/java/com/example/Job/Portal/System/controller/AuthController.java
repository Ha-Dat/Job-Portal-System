package com.example.Job.Portal.System.controller;

import com.example.Job.Portal.System.dto.LoginRequest;
import com.example.Job.Portal.System.dto.RegisterRequest;
import com.example.Job.Portal.System.entity.User;
import com.example.Job.Portal.System.enums.Role;
import com.example.Job.Portal.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping(("/register"))
    public String showRegistrationForm(Model model){
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@Valid @ModelAttribute RegisterRequest registerRequest,
                                      Model model, BindingResult bindingResult){

        // Check validation errors
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Check confirm password
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.registerRequest", "Passwords do not match");
            return "register";
        }

        // Check email if existed
        Optional<User> request = userService.findByEmail(registerRequest.getEmail());
        if(request.isPresent()){
            bindingResult.rejectValue("email", "error.registerRequest", "Email already registered");
            return "register";
        }

        // Create new user with encoded password
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setRole(Role.USER);

        // Save user
        userService.save(user);

        return "redirect:/login?success=true";
    }
}
