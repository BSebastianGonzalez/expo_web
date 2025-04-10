package co.ufps.expo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/kubernetes")
@RequiredArgsConstructor
public class KubernetesController {

    @Autowired
    private Map<String, String> simulatedConfigMap;

    @Autowired
    private Map<String, String> simulatedSecrets;

    @GetMapping("/config")
    public Map<String, String> getConfigMap() {
        return simulatedConfigMap;
    }

    @GetMapping("/info")
    public Map<String, Object> getKubernetesInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("pod", Map.of(
                "name", "demo-pod-" + System.currentTimeMillis() % 1000,
                "namespace", "default",
                "ip", "10.244.0." + (int)(Math.random() * 255)
        ));

        info.put("node", Map.of(
                "name", "minikube",
                "ip", "192.168.49.2"
        ));

        info.put("cluster", Map.of(
                "name", "demo-cluster",
                "version", "v1.26.3"
        ));

        return info;
    }

    @GetMapping("/secrets")
    public Map<String, String> getSecrets() {
        Map<String, String> maskedSecrets = new HashMap<>();
        for (Map.Entry<String, String> entry : simulatedSecrets.entrySet()) {
            maskedSecrets.put(entry.getKey(), "********");
        }
        return maskedSecrets;
    }
}
