package zab.romik.usermanagement.usecases.domain;

import javax.persistence.Embeddable;

/**
 * This credentials required for authentication in system
 */
@Embeddable
public class Credentials {
    /** Unique user name */
    private String username;

    /** Encrypted password */
    private String password;

    private Credentials() {
        // for hibernate
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
