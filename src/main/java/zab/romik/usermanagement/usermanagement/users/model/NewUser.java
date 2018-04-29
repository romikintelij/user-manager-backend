package zab.romik.usermanagement.usermanagement.users.model;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Эта модель используется для того чтобы создавать и обновлять пользователя,
 * потому что здесь есть пароль.
 */
public class NewUser extends UserModel {
    /** Пароль, здесь пароль не кодированный */
    @NotEmpty(groups = ValidationGroups.Create.class)
    private String password;

    /** Группы к которым надо привязать пользователя */
    private Set<Long> groupIds = new HashSet<>();

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
