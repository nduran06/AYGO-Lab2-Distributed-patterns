package eci.aygo.dist.patts.nodeStorageApp.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import eci.aygo.dist.patts.nodeStorageApp.model.User;
import eci.aygo.dist.patts.nodeStorageApp.util.Generator;

@Component
public class ReplicatedUserStorage {

    private static Logger logger = LoggerFactory.getLogger(ReplicatedUserStorage.class);

    private final AtomicLong idGenerator = Generator.getIdGenerator();
	private final ConcurrentHashMap<String, User> userStore = new ConcurrentHashMap<>();

	public User addUser(User entryUser) {

		String userId = String.valueOf(this.idGenerator.incrementAndGet());
		entryUser.setId(userId);
		this.userStore.put(userId, entryUser);
		
    	logger.info("from ReplicatedUserStorage: " + entryUser.toString());
    	logger.info("Total storage: " + this.userStore.toString());

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
}
