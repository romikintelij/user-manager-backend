package zab.romik.usermanagement.usecases.exception;

public class UserNotFound extends BusinessLogicException {
    public UserNotFound(long userId) {
        super("User with id '" + userId + "' not found");
    }
}
