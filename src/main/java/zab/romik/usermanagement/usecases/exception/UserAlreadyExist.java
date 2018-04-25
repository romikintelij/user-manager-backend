package zab.romik.usermanagement.usecases.exception;

public class UserAlreadyExist extends BusinessLogicException {
    public UserAlreadyExist(String username) {
        super("User with username '" + username + "' already exist");
    }
}
