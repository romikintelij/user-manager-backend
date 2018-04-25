package zab.romik.usermanagement.usecases.scenario;

import java.util.Set;

public interface UserGroups {
    void linkUserToGroups(long userId, Set<Long> groupIds);
}
