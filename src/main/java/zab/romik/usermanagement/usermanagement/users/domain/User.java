package zab.romik.usermanagement.usermanagement.users.domain;

import zab.romik.usermanagement.usermanagement.Constants;
import zab.romik.usermanagement.usermanagement.groups.domain.Group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private Credentials credentials;
    private PersonalDetails personalDetails;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    private User() {
        // for hibernate
    }

    public User(Credentials credentials) {
        this.credentials = credentials;
    }

    public void addToGroups(Set<Group> groups) {
        if (groups.isEmpty()) {
            return;
        }

        this.groups.addAll(groups);
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Long getId() {
        return id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }
}
