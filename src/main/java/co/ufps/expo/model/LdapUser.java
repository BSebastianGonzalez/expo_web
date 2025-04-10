package co.ufps.expo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(objectClasses = {"person", "inetOrgPerson"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LdapUser {

    @Id
    private Name dn;

    @Attribute(name = "uid")
    private String userId;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String surname;
}
