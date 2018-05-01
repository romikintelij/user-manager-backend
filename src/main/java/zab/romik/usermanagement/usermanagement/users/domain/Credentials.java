package zab.romik.usermanagement.usermanagement.users.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * class is used to represent user authorization data
 */
@Embeddable
public class Credentials {

    /**
     * name field
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * password field
     */
    @Column(nullable = false)
    private String password;

    /**
     * default class constructor
     */
    private Credentials() {
        // hibernate
    }

    /**
     * class consructor
     * Creates an authorization data object for the user
     *
     * @param username user name, required field
     * @param password user password, required field
     */
    public Credentials(String username, String password) {
        if (isEmpty(username)) {
            throw new IllegalArgumentException("Username must be not null and not empty");
        }

        if (isEmpty(password)) {
            throw new IllegalArgumentException("Password must be not null and not empty");
        }

        this.username = username;
        this.password = password;
    }

    /**
     * class constructor
     * @param source
     */
    public Credentials(Credentials source) {

        this(source.getUsername(), source.getPassword());
    }

    /**
     * checks fields for emptiness
     * @param val
     * @return
     */
    private boolean isEmpty(String val) {
        return val == null || val.isEmpty();
    }

    /**
     * getters ,setters
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
