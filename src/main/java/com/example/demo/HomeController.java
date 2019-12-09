package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;



    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        } else {

            userService.saveAdmin(user);
            model.addAttribute("message", "User Account Created");
        }
        model.addAttribute("messages",messageRepository.findAll());
        return "index";
    }


    @RequestMapping("/")
    public String index(Principal principal, Model model){
        model.addAttribute("messages",messageRepository.findAll());
        String username=principal.getName();
        model.addAttribute("user",userRepository.findByUsernameIgnoreCase(username));
        return "index";
    }



    @RequestMapping("/login")
    public String login(){
        return "login";
    }



    // adding employee and company



    @GetMapping("/addmessage")
    public String addEmployee(Model model, Principal principal){
        model.addAttribute("message", new Message());
        String username=principal.getName();
        model.addAttribute("user",userRepository.findByUsernameIgnoreCase(username));
        return "messageform";
    }

    @PostMapping("/processmessage")
    public String processmessage(@Valid ()@ModelAttribute Message message, BindingResult result, Principal principal){
        String username=principal.getName();

        if(result.hasErrors()){
            return "messageform";
        }
        User user = userRepository.findByUsernameIgnoreCase(username);
        message.setUser(user);
        messageRepository.save(message);

        Set<Message> messages;
        if(user.messages != null){
            messages= new HashSet<>(user.messages);
        }
        else{
            messages = new HashSet<>();
        }

        messages.add(message);
        user.setMessages(messages);
        userRepository.save(user);

        return "redirect:/";
    }

    //Delete, Update and detail student


    @RequestMapping("/detail/{id}")
    public String ViewDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("message",messageRepository.findById(id).get());
        return "detail";
    }
    @RequestMapping("/update/{id}")
    public String Update(@PathVariable("id") long id, Model model, Principal principal){
        Message message=messageRepository.findById(id).get();
        model.addAttribute("message",message);
        model.addAttribute("users", userRepository.findAll());
        String username=principal.getName();
        User user=userRepository.findByUsernameIgnoreCase(username);
        model.addAttribute("user",user);
        if(user.getId()==(message.user.getId())){
        return "messageform";}
        else{
            return "error";
        }
    }
    @RequestMapping("/delete/{id}")
    public String Delete(@PathVariable("id") long id, Principal principal,Model model){
        messageRepository.deleteById(id);
        String username=principal.getName();
        model.addAttribute("user",userRepository.findByUsernameIgnoreCase(username));
        return "redirect:/";
    }

}