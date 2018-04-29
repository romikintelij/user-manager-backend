package zab.romik.usermanagement.usermanagement.groups;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * interface for working with group
 */
public interface Groups {
    /**
     * make select group by id in database
     * @param id
     * @return group
     */
    Optional<Group> findById(long id);

    /**
     * make select group by name in database
     * @param name
     * @return group
     */
    Optional<Group> findByName(String name);

    /**
     * make delete groups of database
     * @param group
     */
    void delete(Group group);

    /**
     *
     * @param group
     * @return save group in a database
     */
    Group save(Group group);

    /**
     * make select all groups by id of database
     * @param ids
     * @return
     */
    Set<Group> findAllByIdIn(Set<Long> ids);

    /**
     * make select all groups of database
     * @return
     */
    List<Group> findAll();
}
