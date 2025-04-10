package co.ufps.expo.controller;


import co.ufps.expo.service.LdapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ldap")
@RequiredArgsConstructor
public class LdapController {

    private final LdapService ldapService;

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, Object>> authenticate(
            @RequestParam String username,
            @RequestParam String password) {

        boolean authenticated = ldapService.authenticate(username, password);

        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", authenticated);
        response.put("username", username);

        return ResponseEntity.ok(response);
    }
}
