package zab.romik.usermanagement.usermanagement.users.domain;

import javax.persistence.Embeddable;

/**
 * Этот класс используется для того чтобы представить данные авторизации пользователя
 */
@Embeddable
public class Credentials {
    private String username;
    private String password;

    private Credentials() {
        // hibernate
    }

    /**
     * Создает объект данных авторизации для пользователя
     *
     * @param username имя пользователя, обязательное поле
     * @param password пароль пользователя, обязательное поле
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

    public Credentials(Credentials source) {
        this(source.getUsername(), source.getPassword());
    }

    private boolean isEmpty(String val) {
        return val == null || val.isEmpty();
    }

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
