package co.ufps.expo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class StorageService {

    private final Map<String, Map<String, String>> storageInstances = new ConcurrentHashMap<>();

    public String createStorageInstance() {
        String instanceId = UUID.randomUUID().toString();
        storageInstances.put(instanceId, new HashMap<>());
        log.info("Creada instancia de almacenamiento: {}", instanceId);
        return instanceId;
    }

    public boolean deleteStorageInstance(String instanceId) {
        if (storageInstances.containsKey(instanceId)) {
            storageInstances.remove(instanceId);
            log.info("Eliminada instancia de almacenamiento: {}", instanceId);
            return true;
        }
        return false;
    }

    public Map<String, String> getStorageInstance(String instanceId) {
        return storageInstances.getOrDefault(instanceId, new HashMap<>());
    }

    public void storeData(String instanceId, String key, String value) {
        Map<String, String> instance = storageInstances.computeIfAbsent(instanceId, k -> new HashMap<>());
        instance.put(key, value);
        log.info("Almacenado dato en instancia {}: {}={}", instanceId, key, value);
    }

    public String retrieveData(String instanceId, String key) {
        Map<String, String> instance = storageInstances.get(instanceId);
        if (instance != null) {
            return instance.get(key);
        }
        return null;
    }

    public Map<String, Object> createServiceBinding(String instanceId, String bindingId) {
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("uri", "http://localhost:8080/storage/" + instanceId);
        credentials.put("access_token", bindingId);

        storeData(instanceId, "binding_" + bindingId, "active");

        log.info("Creado binding {} para instancia {}", bindingId, instanceId);
        return credentials;
    }

    public boolean deleteServiceBinding(String instanceId, String bindingId) {
        if (storageInstances.containsKey(instanceId)) {
            storeData(instanceId, "binding_" + bindingId, "deleted");
            log.info("Eliminado binding {} para instancia {}", bindingId, instanceId);
            return true;
        }
        return false;
    }
}
