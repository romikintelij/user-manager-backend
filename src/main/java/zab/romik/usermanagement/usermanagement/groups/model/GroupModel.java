package zab.romik.usermanagement.usermanagement.groups.model;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;

/**
 * class for representation group
 */
public class GroupModel {
    /**
     * id field
     */
    private long id;
    /**
     * name field
     */
    private String name;

    /**
     * default class constructor
     */
    public GroupModel() {
        // deserialization
    }

    /**
     * class constructor takes the input group
     * @param group
     */
    public GroupModel(Group group) {
        this.id = group.getId();
        this.name = group.getName();
    }

    /**
     * getters and setters
     * @return
     */
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
