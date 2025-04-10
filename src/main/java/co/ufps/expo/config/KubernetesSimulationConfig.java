package co.ufps.expo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KubernetesSimulationConfig {

    @Bean
    public Map<String, String> simulatedConfigMap() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("app.name", "Demo Spring Services");
        configMap.put("app.description", "Demostración de servicios de Spring");
        configMap.put("app.version", "1.0.0");
        configMap.put("app.environment", "development");
        return configMap;
    }

    @Bean
    public Map<String, String> simulatedSecrets() {
        Map<String, String> secrets = new HashMap<>();
        secrets.put("db.username", "admin");
        secrets.put("db.password", "s3cr3t");
        secrets.put("api.key", "k8s-demo-key-123456");
        return secrets;
    }
}
