package zab.romik.usermanagement.usecases.domain;

import zab.romik.usermanagement.hb.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class Group {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_to_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    private Group() {
        // for hibernate
    }

    public Group(String name) {
        this.name = name;
    }

    /**
     * Add user to this group
     *
     * @param user
     */
    public void addUser(User user) {
        Objects.requireNonNull(user, "user must be not null");

        users.add(user);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Group)) return false;

        Group g = (Group) obj;

        return (g.id != null && this.id != null)
                && (g.id.equals(this.id));
    }
}
