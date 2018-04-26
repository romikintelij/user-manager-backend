package zab.romik.usermanagement.usermanagement.groups;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Groups {
    Optional<Group> findById(long id);

    Optional<Group> findByName(String name);

    void delete(Group group);

    Group save(Group group);

    Set<Group> findAllByIdIn(Set<Long> ids);

    List<Group> findAll();
}
