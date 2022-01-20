package com.Vijay.Gluer.Service;

import com.Vijay.Gluer.Exceptions.UserRegistrationException;
import com.Vijay.Gluer.Model.UserRegistration;

import java.util.Optional;

public interface UserRegistrationService {
    public String saveUser(UserRegistration userRegistration) throws UserRegistrationException;
    public UserRegistration getUser(String Email);
    public String updateUserValidation(String Email);
    public String updateUserOnSlashValidation(String Email);
}
