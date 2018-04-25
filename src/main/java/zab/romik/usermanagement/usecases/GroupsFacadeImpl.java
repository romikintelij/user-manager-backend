package zab.romik.usermanagement.usecases;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.repository.GroupsRepository;
import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;
import zab.romik.usermanagement.usecases.scenario.GroupCreator;
import zab.romik.usermanagement.usecases.scenario.GroupUpdater;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupsFacadeImpl implements GroupsFacade {
    private final GroupCreator groupCreator;
    private final GroupsRepository groupsRepository;
    private final GroupUpdater groupUpdater;

    public GroupsFacadeImpl(GroupCreator groupCreator, GroupsRepository groupsRepository,
                            GroupUpdater groupUpdater) {
        this.groupCreator = groupCreator;
        this.groupsRepository = groupsRepository;
        this.groupUpdater = groupUpdater;
    }

    @Override
    public GroupResponse create(GroupRequest req) {
        return groupCreator.create(req);
    }

    @Override
    public List<GroupResponse> loadAllGroups() {
        return groupsRepository.findAll().stream()
                .map(GroupResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long groupId) {
        groupsRepository.deleteById(groupId);
    }

    @Override
    public GroupResponse update(long id, GroupRequest req) {
        return groupUpdater.update(id, req);
    }
}
