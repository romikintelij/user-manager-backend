package zab.romik.usermanagement.usecases.scenario;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.domain.User;
import zab.romik.usermanagement.usecases.exception.UserNotFound;
import zab.romik.usermanagement.usecases.repository.GroupsRepository;
import zab.romik.usermanagement.usecases.repository.UsersRepository;

import java.util.Set;

@Component
public class UserGroupsImpl implements UserGroups {
    private final UsersRepository usersRepository;
    private final GroupsRepository groupsRepository;

    public UserGroupsImpl(UsersRepository usersRepository, GroupsRepository groupsRepository) {
        this.usersRepository = usersRepository;
        this.groupsRepository = groupsRepository;
    }

    @Override
    public void linkUserToGroups(long userId, Set<Long> groupIds) {
        if (groupIds.isEmpty()) {
            // groups is empty, not need find user
            return;
        }

        var user = findUserById(userId);
        user.addToGroups(groupsRepository.findAllByIdIn(groupIds));

        usersRepository.saveAndFlush(user);
    }

    private User findUserById(long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }
}
