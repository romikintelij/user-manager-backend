package zab.romik.usermanagement.usermanagement.users.domain;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * class represent user personal details
 */
@Embeddable
public class PersonalDetails {

    /**
     * first name field
     */
    private String firstName;

    /**
     * last name field
     */
    private String lastName;

    /**
     *  date of birth field
     */
    private LocalDate dateOfBirth;

    /**
     * default class constructor
     */
    private PersonalDetails() {
        // hibernate
    }

    /**
     * class constructor with parameters
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     */
    public PersonalDetails(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * getters
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
