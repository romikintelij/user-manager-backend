package zab.romik.usermanagement.rest.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserGroups {
    @NotNull
    @Size(min = 1)
    private Set<Long> groupIds;

    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
