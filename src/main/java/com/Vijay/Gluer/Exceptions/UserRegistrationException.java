package com.Vijay.Gluer.Exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class UserRegistrationException extends Throwable {
    public void UserAlreadyRegiteredExc(){
        System.out.println("User Already Registered with same email");
    }
}
