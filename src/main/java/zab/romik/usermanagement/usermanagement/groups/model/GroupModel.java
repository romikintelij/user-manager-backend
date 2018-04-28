package zab.romik.usermanagement.usermanagement.groups.model;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;

public class GroupModel {
    private long id;
    private String name;

    public GroupModel() {
        // deserialization
    }

    public GroupModel(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
