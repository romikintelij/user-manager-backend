package zab.romik.usermanagement.usermanagement.users.exception;

import zab.romik.usermanagement.usermanagement.BusinessLogicException;

/**
 * class generate user duplicate exception
 */
public class UserDuplicateException extends BusinessLogicException {
    public UserDuplicateException(String userName) {
        super("User with username '" + userName + "' already found");
    }
}
