package zab.romik.usermanagement.usermanagement.users.exception;

import zab.romik.usermanagement.usermanagement.BusinessLogicException;

public class UserNotFoundException extends BusinessLogicException {
    public UserNotFoundException(long id) {
        super("User with id '" + id + "' not found");
    }
}
