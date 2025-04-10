package co.ufps.expo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LdapService {

    private final LdapTemplate ldapTemplate;

    public boolean authenticate(String username, String password) {
        Filter filter = new EqualsFilter("uid", username);
        try {
            return ldapTemplate.authenticate("ou=users", filter.toString(), password);
        } catch (Exception e) {
            log.error("Error durante la autenticación LDAP", e);
            return false;
        }
    }
}
