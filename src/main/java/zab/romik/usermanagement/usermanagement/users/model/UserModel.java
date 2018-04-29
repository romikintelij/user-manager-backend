package zab.romik.usermanagement.usermanagement.users.model;

import zab.romik.usermanagement.usermanagement.users.domain.PersonalDetails;
import zab.romik.usermanagement.usermanagement.users.domain.User;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Model represents user
 *
 */
public class UserModel {

    /** unique key of user */
    private long id;

    /** user name */
    @NotEmpty
    private String username;

    /** name */
    @NotEmpty
    private String firstName;

    /** last name */
    private String lastName;

    /** date of birth */
    private LocalDate dateOfBirth;

    public UserModel() {
        // for extending
    }

    public UserModel(User source) {
        this.id = source.getId();
        this.username = source.getCredentials().getUsername();

        PersonalDetails personal = source.getPersonalDetails();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
        this.dateOfBirth = personal.getDateOfBirth();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
