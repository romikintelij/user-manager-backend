package zab.romik.usermanagement.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zab.romik.usermanagement.usermanagement.users.Users;
import zab.romik.usermanagement.usermanagement.users.domain.User;

import java.util.Optional;

/**
 * repository for working with database for users
 */
public interface UsersRepository extends JpaRepository<User, Long>, Users {

    /**
     * method selecting user from database by name
     * @param username
     * @return user of database
     */
    @Override
    @Query("SELECT u FROM User u WHERE u.credentials.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
