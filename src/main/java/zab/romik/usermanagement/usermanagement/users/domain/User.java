package zab.romik.usermanagement.usermanagement.users.domain;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * table user in a database
 *
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * id field
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * credentials field
     *
     */
    private Credentials credentials;

    /**
     * personalDetails field
     */
    private PersonalDetails personalDetails;

    /**
     *
     * add table for many to many connection
     */
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    /**
     * default constructor
     */
    private User() {
        // for hibernate
    }

    /**
     * constructor with parameter
     * @param credentials
     */
    public User(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * add to groups
     * @param groups
     */
    public void addToGroups(Set<Group> groups) {
        if (groups.isEmpty()) {
            return;
        }

        this.groups.addAll(groups);
    }

    /**
     * getters,setters,equals,hashcode
     *
     * @return
     */
    public Set<Group> getGroups() {
        return groups;
    }

    public Long getId() {
        return id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = Objects.requireNonNull(credentials, "credentials must be not null");
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
