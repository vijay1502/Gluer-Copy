package com.Vijay.Gluer.Service;

import com.Vijay.Gluer.Exceptions.UserRegistrationException;
import com.Vijay.Gluer.Model.UserRegistration;

import java.util.Optional;

public interface UserRegistrationService {
    public String saveUser(UserRegistration userRegistration) throws UserRegistrationException;

    public UserRegistration getUser(String Email);

    public String updateUserValidation(String Email);

    public String updateUserValidation2(String Encrypted);

    public String updateUserValidation3(String Encrypted);

    public String updateUserOnSlashValidation(String Email);

    public String updateRegisteredName(String Email,UserRegistration userRegistration);

    public String updateRegisteredPassword(String Email,UserRegistration userRegistration);

    public String deleteUser(String email);
}
