package eci.aygo.dist.patts.webClient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @Value("${app.services.load-balancer-url}")
    private String loadBalancerUrl;
    
    @Value("${app.services.websocket-url}")
    private String websocketUrl;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loadBalancerUrl", loadBalancerUrl);
        model.addAttribute("websocketUrl", websocketUrl);
        return "signup";
    }
}
