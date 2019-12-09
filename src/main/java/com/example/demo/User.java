package com.example.demo;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String username;
    private boolean enabled;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Set<Message> messages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Collection<Role> roles;



    public User() {
    }

    public User( String email,String password, String firstName, String lastName,   boolean enabled, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.username = username;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
