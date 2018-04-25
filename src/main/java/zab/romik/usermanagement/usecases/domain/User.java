package zab.romik.usermanagement.usecases.domain;

import zab.romik.usermanagement.hb.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This is root entity
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    /**
     * Credentials for authorization
     */
    private Credentials credentials;

    /**
     * First name, last name and another data
     */
    private PersonalDetails personalDetails;

    /**
     * Groups in which this user is composed
     */
    @ManyToMany
    @JoinTable(
            name = "users_to_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    private User() {
        // for hibernate
    }

    public User(Credentials credentials, PersonalDetails personalDetails) {
        this(credentials, personalDetails, new HashSet<>());
    }

    public User(Credentials credentials, PersonalDetails personalDetails,
                Set<Group> groups) {
        this.credentials = credentials;
        this.personalDetails = personalDetails;
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    /**
     * This method should return username from credentials
     *
     * @return non-null username
     */
    public String getUsername() {
        return credentials.getUsername();
    }

    /**
     * Add user to given group, this method may throw {@code NPE}
     * when given user is {@code null}
     *
     * @param group where user should be added
     * @throws NullPointerException when given group is {@code null}
     */
    public void addToGroup(Group group) {
        Objects.requireNonNull(group, "group must be not null");

        groups.add(group);
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void addToGroups(Set<Group> groups) {
        if (groups.isEmpty())
            return;

        this.groups.addAll(groups);
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        Objects.requireNonNull(personalDetails, "Personal details must be not null");

        this.personalDetails = personalDetails;
    }
}
