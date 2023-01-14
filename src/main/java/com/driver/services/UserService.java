package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){
            userRepository3.deleteById(userId);
    }

    public void updateUser(User user){
        int userId = user.getId();
        User updatedUser = userRepository3.findById(userId).get();
        if(updatedUser==null){
            updatedUser = userRepository3.findByUsername(user.getUsername());
        }
        updatedUser.setId(user.getId());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword((user.getPassword()));
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setBlogList(user.getBlogList());
        userRepository3.save(updatedUser);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}
