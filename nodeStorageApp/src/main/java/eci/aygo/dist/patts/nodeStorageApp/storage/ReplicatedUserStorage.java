package eci.aygo.dist.patts.nodeStorageApp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import eci.aygo.dist.patts.nodeStorageApp.model.ReplicationRequest;
import eci.aygo.dist.patts.nodeStorageApp.model.User;

@Component
public class ReplicatedUserStorage {

	private static Logger logger = LoggerFactory.getLogger(ReplicatedUserStorage.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${server.port}")
    private int currentPort;
	
	@Value("${spring.application.name}")
    private String serviceName;

	private final ConcurrentHashMap<String, User> userStore = new ConcurrentHashMap<>();

	public User addUser(User entryUser) {

		this.userStore.put(entryUser.getId(), entryUser);
		replicateToOtherNodes(entryUser.getId(), entryUser);

		logger.info("from ReplicatedUserStorage, request: " + entryUser.toString());

		return entryUser;
	}

	public void putUser(User user) {
		this.userStore.put(user.getId(), user);
	}

	public User getUser(String userId) {
		return this.userStore.get(userId);
	}

	public User deleteUser(String id) {
		return this.userStore.remove(id);
	}

	public List<User> getAllUsers() {
		return new ArrayList<>(this.userStore.values());
	}

	private void replicateToOtherNodes(String userId, User user) {
		logger.info("from discoveryClient.getInstances " + discoveryClient.getInstances(this.serviceName));
		discoveryClient.getInstances(this.serviceName).stream().filter(instance -> instance.getPort() != this.currentPort)
		
				.forEach(instance -> {
					try {
						String url = instance.getUri() + "/api/storage/replicate";
						logger.info("url ", url);
						restTemplate.postForObject(url, new ReplicationRequest(userId, user), String.class);
					} catch (Exception e) {
						logger.error("Failed to replicate to node: {}", instance.getUri(), e);
					}
				});
	}
	
    public void handleReplication(ReplicationRequest request) {
        this.putUser(request.getUser());
        logger.info("from ReplicatedUserStorage, total storage: " + this.userStore.toString());
    }
}
