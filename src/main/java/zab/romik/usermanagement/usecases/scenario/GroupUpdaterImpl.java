package zab.romik.usermanagement.usecases.scenario;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.exception.GroupNotFound;
import zab.romik.usermanagement.usecases.repository.GroupsRepository;
import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;

@Component
public class GroupUpdaterImpl implements GroupUpdater {
    private final GroupsRepository groupsRepository;

    public GroupUpdaterImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public GroupResponse update(long id, GroupRequest req) {
        var group = groupsRepository.findById(id).orElseThrow(() -> new GroupNotFound(id));

        group.setName(req.getName());
        groupsRepository.saveAndFlush(group);

        return GroupResponse.of(group);
    }
}
