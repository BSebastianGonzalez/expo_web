package co.ufps.expo.service;

import co.ufps.expo.model.LdapUser;
import co.ufps.expo.repository.LdapUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LdapUserService {

    private final LdapUserRepository repository;

    public List<LdapUser> getAllUsers() {
        return repository.findAll();
    }

    public LdapUser getByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public LdapUser createUser(String userId, String cn, String sn) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "users")
                .add("uid", userId)
                .build();

        LdapUser user = new LdapUser(dn, userId, cn, sn);
        return repository.save(user);
    }

    public void deleteUser(Name dn) {
        repository.deleteById(dn);
    }
}
