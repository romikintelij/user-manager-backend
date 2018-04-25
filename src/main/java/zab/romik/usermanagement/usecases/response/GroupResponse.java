package zab.romik.usermanagement.usecases.response;

import zab.romik.usermanagement.usecases.domain.Group;

public class GroupResponse {
    private long id;
    private String name;

    private GroupResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GroupResponse of(Group group) {
        return new GroupResponse(group.getId(), group.getName());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
