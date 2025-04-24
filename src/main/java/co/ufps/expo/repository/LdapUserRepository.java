package co.ufps.expo.repository;

import co.ufps.expo.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;

public interface LdapUserRepository extends LdapRepository<LdapUser> {
    LdapUser findByUserId(String userId);
}
