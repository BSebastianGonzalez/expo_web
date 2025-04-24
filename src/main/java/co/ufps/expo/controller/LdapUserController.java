package co.ufps.expo.controller;

import co.ufps.expo.model.LdapUser;
import co.ufps.expo.service.LdapUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ldap/users")
@RequiredArgsConstructor
public class LdapUserController {

    private final LdapUserService service;

    @GetMapping
    public List<LdapUser> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public LdapUser getByUserId(@PathVariable String userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public LdapUser create(@RequestParam String userId,
                           @RequestParam String cn,
                           @RequestParam String sn) {
        return service.createUser(userId, cn, sn);
    }

    @DeleteMapping
    public void delete(@RequestParam String dn) throws Exception {
        service.deleteUser(new javax.naming.ldap.LdapName(dn));
    }
}
