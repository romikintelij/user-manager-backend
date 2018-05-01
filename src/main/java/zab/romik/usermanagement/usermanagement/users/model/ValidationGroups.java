package zab.romik.usermanagement.usermanagement.users.model;

import javax.validation.groups.Default;

/**hibernate group validator*/
public final class ValidationGroups {
    private ValidationGroups() {
        throw new AssertionError();
    }

    public interface Create extends Default {}
}
