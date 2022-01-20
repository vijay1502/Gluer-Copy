package com.Vijay.Gluer.Controller;

import com.Vijay.Gluer.Model.UserLogin;
import com.Vijay.Gluer.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gluer/login")
public class UserLoginController {

    private UserLoginService userLoginService;

    public UserLoginController() {
    }

    @Autowired
    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService=userLoginService;
    }
    @GetMapping("/status")
    public ResponseEntity<String> getLoginStatus(@RequestBody UserLogin userLogin){
        String status = userLoginService.UserLogin(userLogin);
        return new ResponseEntity<String>(status,HttpStatus.ACCEPTED);
    }
}
