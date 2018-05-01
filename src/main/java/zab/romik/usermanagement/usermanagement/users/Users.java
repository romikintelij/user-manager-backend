package zab.romik.usermanagement.usermanagement.users;

import zab.romik.usermanagement.usermanagement.users.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * This interface should be used to access data. In the package business
 *logic, this interface should not be implemented, because our layer of business
 *logic should not depend on the databases and interfaces of the framework, this is for us
 *is done on the first level of architecture
 */
public interface Users {
    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    void delete(User user);

    User save(User user);

    List<User> findAll();
}
