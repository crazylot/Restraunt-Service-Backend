package com.microservices.swiggy.identityservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.swiggy.identityservice.dto.request.AuthRequest;
import com.microservices.swiggy.identityservice.dto.response.ResponseTemplateDto;
import com.microservices.swiggy.identityservice.entity.UserCredential;
import com.microservices.swiggy.identityservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	AuthService service;
	BCryptPasswordEncoder bcrypt;
	
	@Autowired
	public AuthController(AuthService service, BCryptPasswordEncoder bcrypt){
	      this.service = service;
	      this.bcrypt = bcrypt;

	    }

    @PostMapping("/register")
    public ResponseTemplateDto addNewUser(@RequestBody UserCredential user) {
        return service.saveUserCredentials(user);
    }

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
    	try {
    		UserCredential user = service.getUserByEmail(authRequest.getEmail());
        	boolean isPasswordMatches = bcrypt.matches(authRequest.getPassword(), user.getPassword());
        	Optional<UserCredential> optUser = Optional.ofNullable(user);
        	if(optUser.isEmpty()) {
        		throw new RuntimeException("user does not exists");
        	}
        	if(isPasswordMatches) {
        		var token = service.generateToken(authRequest.getEmail());
        		return ResponseEntity.ok().body(token);
        	}else {
        		return ResponseEntity.badRequest().build();
        	}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}