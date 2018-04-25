package zab.romik.usermanagement.usecases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usecases.domain.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByCredentialsUsername(String username);
}
