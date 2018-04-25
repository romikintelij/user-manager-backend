package zab.romik.usermanagement.usecases.response;

import zab.romik.usermanagement.usecases.domain.User;

import java.time.LocalDate;

/**
 * Created user response, required for send to client
 */
public class UserResponse {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public UserResponse(long id, String username, String firstName,
                        String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public static UserResponse of(User user) {
        var personalDetails = user.getPersonalDetails();

        return new UserResponse(user.getId(), user.getUsername(),
                personalDetails.getFirstName(), personalDetails.getLastName(),
                personalDetails.getDateOfBirth());
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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
