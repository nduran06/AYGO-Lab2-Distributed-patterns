package eci.aygo.dist.patts.webClient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import eci.aygo.dist.patts.webClient.model.User;

@Controller
public class WebController {

	private static Logger logger = LoggerFactory.getLogger(WebController.class);

	@Value("${app.services.load-balancer-url}")
	private String loadBalancerUrl;

	@Value("${app.services.websocket-url}")
	private String websocketUrl;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("loadBalancerUrl", loadBalancerUrl);
		model.addAttribute("websocketUrl", websocketUrl);
		return "signup";
	}

	@PostMapping("/user/create")
    public ResponseEntity<?> saveStudent(@RequestBody User user) {
        
        try {
            // Forward the request to load balancer
            String url = loadBalancerUrl + "/api/users/create";
            ResponseEntity<?> response = restTemplate.postForEntity(url, user, String.class);
            logger.info("from WebController:" + response.getBody());
            
			return response;
        } 
        
        catch (Exception e) {
        	logger.error("Error during registration", e);
            return ResponseEntity.internalServerError()
                .body("Registration failed: " + e.getMessage());
        }
    }
}
