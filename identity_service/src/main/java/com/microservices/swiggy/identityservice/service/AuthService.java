package com.microservices.swiggy.identityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.swiggy.identityservice.dto.response.ResponseTemplateDto;
import com.microservices.swiggy.identityservice.entity.UserCredential;
import com.microservices.swiggy.identityservice.repository.UserCredentialRepository;

@Service
public class AuthService {
	
	UserCredentialRepository repository;
	PasswordEncoder passwordEncoder;
	JwtService jwtService;
	
	@Autowired
	public AuthService(UserCredentialRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
	      this.repository = repository;
	      this.passwordEncoder = passwordEncoder;
	      this.jwtService =jwtService;
	    }
	
	public UserCredential getUserByEmail(String email) {
		return repository.findByemail(email);
	}
	
	
	public ResponseTemplateDto saveUserCredentials(UserCredential user ) {
		ResponseTemplateDto responseTemplateDto = new ResponseTemplateDto();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		responseTemplateDto.setUsername(user.getName());
		responseTemplateDto.setMessage("user credentials added in system");
		return responseTemplateDto;
	}
	
	public String generateToken(String email) {
		return jwtService.generateToken(email);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
