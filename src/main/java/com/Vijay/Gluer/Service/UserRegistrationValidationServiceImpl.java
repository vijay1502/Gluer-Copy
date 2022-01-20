package com.Vijay.Gluer.Service;

import com.Vijay.Gluer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRegistrationValidationServiceImpl implements UserRegistrationValidationService{
    private UserRepository userRepository;

    @Autowired
    public UserRegistrationValidationServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public String updateUserValidation(String Email) {
        return null;
    }
}
