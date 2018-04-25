package zab.romik.usermanagement.usecases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zab.romik.usermanagement.usecases.domain.Group;

import java.util.Optional;
import java.util.Set;

public interface GroupsRepository extends JpaRepository<Group, Long> {
    Set<Group> findAllByIdIn(Set<Long> ids);

    Optional<Group> findByName(String name);
}
