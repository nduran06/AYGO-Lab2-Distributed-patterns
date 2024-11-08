package eci.aygo.dist.patts.nodeStorageApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eci.aygo.dist.patts.nodeStorageApp.config.WebSocketHandler;
import eci.aygo.dist.patts.nodeStorageApp.model.User;
import eci.aygo.dist.patts.nodeStorageApp.storage.ReplicatedUserStorage;

@Service
public class UserService {
	
	@Autowired
	private ReplicatedUserStorage userStorage;
	
	/*@Autowired
    private WebSocketHandler webSocketHandler;
*/
    public void addUser(User entryUser) {
  
        userStorage.addUser(entryUser);
      // webSocketHandler.notifyAllClients(entryUser);
    }

}
