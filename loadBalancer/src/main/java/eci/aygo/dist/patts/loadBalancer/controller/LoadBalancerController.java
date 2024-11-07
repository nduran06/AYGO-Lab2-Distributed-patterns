package eci.aygo.dist.patts.loadBalancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import eci.aygo.dist.patts.loadBalancer.model.User;

@RestController
@RequestMapping("/api/users")
public class LoadBalancerController {
	
	private DiscoveryClient discoveryClient;
	@Autowired
    private RestTemplate restTemplate;
    private int currentServerIndex = 0;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User registration) {
        List<ServiceInstance> instances = discoveryClient.getInstances("nodeStorageApp-service");
        
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                               .body("No storage servers available");
        }
        
        ServiceInstance instance = instances.get(currentServerIndex % instances.size());
        currentServerIndex++;
        
        String registrationUrl = instance.getUri() + "/api/registration";
        return restTemplate.postForEntity(registrationUrl, registration, String.class);
    }

}
