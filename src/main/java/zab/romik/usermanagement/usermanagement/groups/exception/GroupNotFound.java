package zab.romik.usermanagement.usermanagement.groups.exception;

import zab.romik.usermanagement.usermanagement.MissingDataException;

public class GroupNotFound extends MissingDataException {
    public GroupNotFound(long groupId) {
        super("Group with id '" + groupId + "' not found");
    }
}
