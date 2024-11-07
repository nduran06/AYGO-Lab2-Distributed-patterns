package eci.aygo.dist.patts.nodeStorageApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eci.aygo.dist.patts.nodeStorageApp.model.User;
import eci.aygo.dist.patts.nodeStorageApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
    	
    	logger.info("from UserController: " + user.toString());
    	userService.addUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}