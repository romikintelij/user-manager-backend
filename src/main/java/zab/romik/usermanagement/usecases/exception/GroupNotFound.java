package zab.romik.usermanagement.usecases.exception;

public class GroupNotFound extends BusinessLogicException {
    public GroupNotFound(long id) {
        super("Group with id '" + id + "' not found in system");
    }
}
