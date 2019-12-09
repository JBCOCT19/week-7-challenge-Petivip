package com.example.demo;




import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String pastedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user;

    public Message() {
    }

    public Message(String title, String message, String pastedDate, User user) {
        this.title = title;
        this.description=message;
        this.pastedDate = pastedDate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPastedDate() {
        return pastedDate;
    }

    public void setPastedDate(String pastedDate) {
        this.pastedDate = pastedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
