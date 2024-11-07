package eci.aygo.dist.patts.nodeStorageApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NodeStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeStorageApplication.class, args);
	}

}
