package co.ufps.expo.controller;

import co.ufps.expo.model.Usuario;
import co.ufps.expo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@RefreshScope
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Value("${mensaje:Valor por defecto}")
    private String mensaje;

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Exposicion - Servicios Spring Boot");
        response.put("servicios", getServicios());
        return response;
    }


    //Endpoints Spring Data JPA
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario userDetails) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(id, userDetails);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //Endpoint Spring Cloud Config
    @GetMapping("/config")
    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("mensaje", mensaje);
        return config;
    }

    //Endpoint Spring Cloud Kubernetes
    @GetMapping("/kubernetes")
    public Map<String, String> getKubernetesInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("estado", "Configurado para detectar ConfigMaps y Secrets");
        return info;
    }

    //Endpoint Spring Cloud Open Service Broker
    @GetMapping("/servicios")
    public Map<String, String> getServicios() {
        Map<String, String> servicios = new HashMap<>();
        servicios.put("Spring Data JPA", "/api/usuarios");
        servicios.put("Spring Cloud Task", "/task/stats");
        servicios.put("Spring Cloud Config", "/config");
        servicios.put("Spring LDAP", "/api/ldap/authenticate?username=user1&password=password1");
        servicios.put("Spring Cloud Kubernetes", "/kubernetes/config");
        servicios.put("Spring Cloud Open Service Broker", "/storage/instances");
        servicios.put("Spring Cloud Skipper", "/skipper/apps/{appName}/versions");
        return servicios;
    }

    // Endpoint Spring Cloud Skipper
    @GetMapping("/skipper")
    public Map<String, String> getSkipperInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("estado", "Cliente configurado");
        return info;
    }

    // Endpoint Spring LDAP
    @GetMapping("/ldap")
    public Map<String, String> getLdapInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("estado", "Configurado");
        return info;
    }
}
