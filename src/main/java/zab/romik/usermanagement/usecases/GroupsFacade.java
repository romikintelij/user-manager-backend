package zab.romik.usermanagement.usecases;

import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;

import java.util.List;

public interface GroupsFacade {
    GroupResponse create(GroupRequest req);

    List<GroupResponse> loadAllGroups();

    void delete(long groupId);

    GroupResponse update(long id, GroupRequest req);
}
