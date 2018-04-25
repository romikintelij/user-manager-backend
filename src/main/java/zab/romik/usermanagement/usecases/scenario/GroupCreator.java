package zab.romik.usermanagement.usecases.scenario;

import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;

public interface GroupCreator {
    GroupResponse create(GroupRequest model);
}
