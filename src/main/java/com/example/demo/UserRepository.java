package com.example.demo;


import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameIgnoreCase(String name);
}
