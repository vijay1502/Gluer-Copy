package com.Vijay.Gluer.Controller;

import com.Vijay.Gluer.Exceptions.UserRegistrationException;
import com.Vijay.Gluer.Model.UserRegistration;
import com.Vijay.Gluer.Service.UserRegistrationService;
import com.Vijay.Gluer.Service.UserRegistrationServiceImpl;
import com.Vijay.Gluer.Service.UserRegistrationValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gluser")
public class UserRegistrationController {
private UserRegistrationService userRegistrationService;
public UserRegistrationController() {

}
@Autowired
public UserRegistrationController(UserRegistrationService userRegistrationService){
    this.userRegistrationService=userRegistrationService;
}

@PostMapping("/save")
public ResponseEntity<String> saveUsers(@RequestBody UserRegistration userRegistration) throws UserRegistrationException {
String userRegistration1=userRegistrationService.saveUser(userRegistration);
return new ResponseEntity<>(userRegistration1, HttpStatus.CREATED);
}

@GetMapping("/user/{email}")
public ResponseEntity<?> getUser(@PathVariable String email){
UserRegistration userRegistration=userRegistrationService.getUser(email);
return new ResponseEntity<>(userRegistration,HttpStatus.OK);
}

@GetMapping("/verify/{encryptor}")
    public ResponseEntity<String> updateValidated(@PathVariable String encryptor) {

    String validate=userRegistrationService.updateUserValidation(encryptor);
    return new ResponseEntity<>(validate,HttpStatus.OK);
}

    @GetMapping("/glueme/verify/{email}")
    public ResponseEntity<String> updateUnvalidated(@PathVariable String email){
        String validate=userRegistrationService.updateUserOnSlashValidation(email);
        return new ResponseEntity<>(validate,HttpStatus.ACCEPTED);
    }


}
