package eci.aygo.dist.patts.nodeStorageApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eci.aygo.dist.patts.nodeStorageApp.model.ReplicationRequest;
import eci.aygo.dist.patts.nodeStorageApp.storage.ReplicatedUserStorage;

@RestController
@RequestMapping("/api/storage")
public class ReplicatedUserStorageController {
	
	private static Logger logger = LoggerFactory.getLogger(ReplicatedUserStorage.class);

	@Autowired
    private ReplicatedUserStorage replicatedUserStorage;
	
    @PostMapping("/replicate")  // Note: not /api/storage/replicate
    public ResponseEntity<String> handleReplication(@RequestBody ReplicationRequest request) {
        try {
        	logger.debug("Received replication request for key: {}", request.getUser());
        	replicatedUserStorage.handleReplication(request);
            return ResponseEntity.ok("Replicated successfully");
        } 
        
        catch (Exception e) {
        	logger.error("Error handling replication", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
