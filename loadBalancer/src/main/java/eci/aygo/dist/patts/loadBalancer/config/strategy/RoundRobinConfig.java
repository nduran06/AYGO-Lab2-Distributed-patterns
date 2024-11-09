package eci.aygo.dist.patts.loadBalancer.config.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class RoundRobinConfig {
	
	@Bean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }

}
