package com.shopcartsystem.controller;

import com.shopcartsystem.dto.AuthRequest;
import com.shopcartsystem.dto.Product;
import com.shopcartsystem.entity.UserInfo;
import com.shopcartsystem.service.JwtService;
import com.shopcartsystem.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo) throws Exception {
    	String response= service.addUser(userInfo);
    	logger.info("Response: {}*****************************************************", response);
        return ResponseEntity.ok("{\"response\": \"" + response + "\"}");
    }

//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public List<Product> getAllTheUsers() {
//        return service.getProducts();
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public Product getProductById(@PathVariable int id) {
//        return service.getProduct(id);
//    }

    @GetMapping("/info")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userName = jwtService.extractUsername(token);
        logger.info("Response: {}", userName);
        return ResponseEntity.ok("{\"userName\": \"" + userName + "\"}");
    }

    @GetMapping("/checkIfTokenExpired")
    public ResponseEntity<String> getExpirationStatus(@RequestHeader("Authorization") String authorizationHeader){
    	String token = authorizationHeader.replace("Bearer ", "");
    	Boolean status = jwtService.isTokenExpired(token);
    	return ResponseEntity.ok("{\"status\":"  + status + "}");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok("{\"token\": \"" + token + "\"}");
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
    @GetMapping("/isAdmin/{name}")
    public ResponseEntity<String> isAdmin(@PathVariable String name) {
    	Boolean res=  jwtService.isAdmin(name);
    	return ResponseEntity.ok("{\"res\": \"" + res + "\"}");
    }
    
 
}
