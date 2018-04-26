package zab.romik.usermanagement.usermanagement.users.domain;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PersonalDetails {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private PersonalDetails() {
        // hibernate
    }

    public PersonalDetails(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

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
