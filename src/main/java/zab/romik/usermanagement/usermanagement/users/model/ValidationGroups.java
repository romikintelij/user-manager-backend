package zab.romik.usermanagement.usermanagement.users.model;

import javax.validation.groups.Default;

// fixme: наверное лучше вынести за пределы бизнес логики
public final class ValidationGroups {
    private ValidationGroups() {
        throw new AssertionError();
    }

    public interface Create extends Default {}
}
