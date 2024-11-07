package eci.aygo.dist.patts.loadBalancer.controller;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import eci.aygo.dist.patts.loadBalancer.model.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoadBalancerController {
	
	private final DiscoveryClient discoveryClient = null;
    private int currentServerIndex = 0;
	
	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User registration) {
        List<ServiceInstance> instances = discoveryClient.getInstances("storage-service");
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("No storage servers available");
        }
        
        // Round-robin selection
        ServiceInstance instance = instances.get(currentServerIndex % instances.size());
        currentServerIndex++;
        
        // Forward request to selected instance
        String baseUrl = instance.getUri().toString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(baseUrl + "/store", registration, String.class);
    }

}
