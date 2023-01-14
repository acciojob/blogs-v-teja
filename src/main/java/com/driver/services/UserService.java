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
        User updatedUser = null;
        try{
            updatedUser = userRepository3.findById(user.getId()).get();
            if(updatedUser==null){
                userRepository3.save(user);
            }else{
                updatedUser.setUsername(user.getUsername());
                updatedUser.setPassword((user.getPassword()));
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setBlogList(user.getBlogList());
                userRepository3.save(updatedUser);
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}
