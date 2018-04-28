package zab.romik.usermanagement.usermanagement.users.exception;

import zab.romik.usermanagement.usermanagement.MissingDataException;

public class UserNotFoundException extends MissingDataException {
    public UserNotFoundException(long id) {
        super("User with id '" + id + "' not found");
    }
}
