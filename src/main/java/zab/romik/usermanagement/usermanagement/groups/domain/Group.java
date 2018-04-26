package zab.romik.usermanagement.usermanagement.groups.domain;

import zab.romik.usermanagement.usermanagement.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private long id;
    private String name;

    private Group() {
        // hibernate
    }

    public Group(String name) {
        this.name = name;
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
