package com.Vijay.Gluer.Service;

import com.Vijay.Gluer.Model.UserLogin;
import com.Vijay.Gluer.Model.UserRegistration;
import com.Vijay.Gluer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImplementation implements UserLoginService{

    private UserRepository userRepository;

    @Autowired
    public UserLoginServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String UserLogin(UserLogin userLogin) {
        if (userRepository.existsById(userLogin.getEmail())) {
            UserRegistration user = userRepository.findById(userLogin.getEmail()).get();
            if (user.getValidated().equals("Yes") && user.getPassword().equals(userLogin.getPassword())) {
                return "Hi " + user.getName() + ", You're successfully Logged in";
            } else if (user.getValidated().equals("No")) {
                return "Hi " + user.getName() + ", You are not validated yet";
            } else{
                return "Hi, your credentials are incorrect";
            }
        } else {
            return "Email not clear/not found,recheck or please Create an account, Get verified and Login in";
        }
    }
}
