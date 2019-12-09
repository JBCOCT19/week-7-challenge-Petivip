package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        //first user
       User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(adminRole));

        Message message=new Message("Java as programming Language","It is one of the intersitng and basic languages","12/08/2019",user);
        messageRepository.save(message);
        Set<Message> mess=new HashSet<>();
        mess.add(message);
        user.setMessages(mess);
        userRepository.save(user);

        //second user
        user = new User("admin@admin.com", "pass", "Admin", "User",
                true, "admin");
        user.setRoles(Arrays.asList(adminRole));
         message=new Message("pyhotn as programming Language","It is an easy and simpple languages","12/08/2019",user);
        messageRepository.save(message);
        mess=new HashSet<>();
        mess.add(message);
        user.setMessages(mess);
        userRepository.save(user);


        //third user
        user = new User("petivip@admin.com", "pass", "petivip", "petivip",
                true, "petivip");
        user.setRoles(Arrays.asList(adminRole));

         message=new Message("Javascrip as programming Language","It is useful for web browser","12/08/2019",user);
        messageRepository.save(message);
        mess=new HashSet<>();
        mess.add(message);
        user.setMessages(mess);
        userRepository.save(user);


    }

}

