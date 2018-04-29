package zab.romik.usermanagement.usermanagement.groups.exception;

import zab.romik.usermanagement.usermanagement.MissingDataException;

/**
 * class making exception  group not found
 */
public class GroupNotFound extends MissingDataException {
    public GroupNotFound(long groupId) {
        super("Group with id '" + groupId + "' not found");
    }
}
