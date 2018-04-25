package zab.romik.usermanagement.usecases.scenario;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.domain.Group;
import zab.romik.usermanagement.usecases.exception.GroupAlreadyExist;
import zab.romik.usermanagement.usecases.repository.GroupsRepository;
import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;

@Component
public class GroupCreatorImpl implements GroupCreator {
    private final GroupsRepository groupsRepository;

    public GroupCreatorImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public GroupResponse create(GroupRequest model) {
        assertNotFoundGroupDuplicate(model);

        var groupEntity = persist(model);
        // create group representation
        return GroupResponse.of(groupEntity);
    }

    private Group persist(GroupRequest model) {
        var groupEntity = new Group(model.getName());
        return groupsRepository.saveAndFlush(groupEntity);
    }

    private void assertNotFoundGroupDuplicate(GroupRequest model) {
        groupsRepository.findByName(model.getName())
            .ifPresent((group) -> {
                // duplicated group
                throw new GroupAlreadyExist(group.getName());
            });
    }
}
