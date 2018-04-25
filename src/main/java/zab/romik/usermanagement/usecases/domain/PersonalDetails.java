package zab.romik.usermanagement.usecases.domain;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class PersonalDetails {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private PersonalDetails() {
        // for hibernate
    }

    private PersonalDetails(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = Objects.requireNonNull(firstName, "First name should be not null");
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Static factory method for full personal details
     *
     * @param firstName   required first name
     * @param lastName    nullable last name
     * @param dateOfBirth nullable date of birth
     * @return instance of personal details
     */
    public static PersonalDetails of(String firstName, String lastName, LocalDate dateOfBirth) {
        return new PersonalDetails(firstName, lastName, dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalDetails setFirstName(String firstName) {
        return new PersonalDetails(firstName, lastName, dateOfBirth);
    }

    public String getLastName() {
        return lastName;
    }

    public PersonalDetails setLastName(String lastName) {
        return new PersonalDetails(firstName, lastName, dateOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public PersonalDetails setDateOfBirth(LocalDate dateOfBirth) {
        return new PersonalDetails(firstName, lastName, dateOfBirth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalDetails)) return false;
        PersonalDetails that = (PersonalDetails) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }
}
