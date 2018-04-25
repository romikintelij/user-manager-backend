package zab.romik.usermanagement.usecases.exception;

public class GroupAlreadyExist extends BusinessLogicException {
    public GroupAlreadyExist(String groupName) {
        super("Group with name '" + groupName + "' already exist");
    }
}
