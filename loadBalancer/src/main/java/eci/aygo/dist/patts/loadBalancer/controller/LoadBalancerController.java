package eci.aygo.dist.patts.loadBalancer.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import eci.aygo.dist.patts.loadBalancer.model.User;
import eci.aygo.dist.patts.loadBalancer.util.Generator;

@RestController
@RequestMapping("/api/users")
@Component
public class LoadBalancerController {
	
    private static Logger logger = LoggerFactory.getLogger(LoadBalancerController.class);
	private final AtomicLong idGenerator = Generator.getIdGenerator();

	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		
		try {
			List<String> services = discoveryClient.getServices();
			return ResponseEntity.ok("Available services: " + services.toString() + ", Available userstorageapp-service instances: " + discoveryClient.getInstances("userstorageapp-service").toString());
		} 
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@PostMapping("/create")
	public ResponseEntity<String> registerUser(@RequestBody User entryUser) {
		
		try {
			logger.info("Available services: {}", discoveryClient.getServices());
			List<ServiceInstance> instances = discoveryClient.getInstances("userstorageapp-service");
			logger.info("Found instances: {}", instances);
			
			entryUser.setId(String.valueOf(this.idGenerator.incrementAndGet()));
			
			return restTemplate.postForEntity("http://userstorageapp-service/api/users/create", entryUser, String.class);
		} 
		
		catch (Exception e) {
			logger.error("Error in registration", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Registration failed: " + e.getMessage());
		}
	}
}