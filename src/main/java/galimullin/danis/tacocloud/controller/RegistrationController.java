package galimullin.danis.tacocloud.controller;

import galimullin.danis.tacocloud.model.RegistrationForm;
import galimullin.danis.tacocloud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String processRegister(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
