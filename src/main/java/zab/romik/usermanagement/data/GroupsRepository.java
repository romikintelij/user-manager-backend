package zab.romik.usermanagement.data;

import org.springframework.data.jpa.repository.JpaRepository;
import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.Groups;

public interface GroupsRepository extends JpaRepository<Group, Long>, Groups {
}
