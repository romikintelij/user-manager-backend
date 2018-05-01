package zab.romik.usermanagement.usermanagement.users.model;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *This model is used to create and update a user
 *
 */
public class NewUser extends UserModel {
    /** password field must be not empty */
    @NotEmpty(groups = ValidationGroups.Create.class)
    private String password;

    /** groups to which you must bind the user */
    private Set<Long> groupIds = new HashSet<>();

    /** getters,setters*/
    public Set<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<Long> groupIds) {
        this.groupIds = groupIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
