package co.ufps.expo.controller;


import co.ufps.expo.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping("/{instanceId}")
    public ResponseEntity<Map<String, String>> getStorageInstance(@PathVariable String instanceId) {
        Map<String, String> instance = storageService.getStorageInstance(instanceId);
        if (instance != null) {
            return ResponseEntity.ok(instance);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{instanceId}")
    public ResponseEntity<Void> storeData(
            @PathVariable String instanceId,
            @RequestParam String key,
            @RequestParam String value) {
        storageService.storeData(instanceId, key, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{instanceId}/{key}")
    public ResponseEntity<String> retrieveData(
            @PathVariable String instanceId,
            @PathVariable String key) {
        String value = storageService.retrieveData(instanceId, key);
        if (value != null) {
            return ResponseEntity.ok(value);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoints para simular Service Broker

    @PostMapping("/instances")
    public ResponseEntity<Map<String, Object>> createInstance() {
        String instanceId = storageService.createStorageInstance();

        Map<String, Object> response = new HashMap<>();
        response.put("instance_id", instanceId);
        response.put("dashboard_url", "http://localhost:8080/storage/" + instanceId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/instances/{instanceId}")
    public ResponseEntity<Void> deleteInstance(@PathVariable String instanceId) {
        boolean deleted = storageService.deleteStorageInstance(instanceId);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/instances/{instanceId}/bindings")
    public ResponseEntity<Map<String, Object>> createBinding(@PathVariable String instanceId) {
        String bindingId = UUID.randomUUID().toString();
        Map<String, Object> credentials = storageService.createServiceBinding(instanceId, bindingId);

        Map<String, Object> response = new HashMap<>();
        response.put("binding_id", bindingId);
        response.put("credentials", credentials);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/instances/{instanceId}/bindings/{bindingId}")
    public ResponseEntity<Void> deleteBinding(
            @PathVariable String instanceId,
            @PathVariable String bindingId) {
        boolean deleted = storageService.deleteServiceBinding(instanceId, bindingId);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
