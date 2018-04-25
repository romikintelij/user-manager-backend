package zab.romik.usermanagement.usecases.scenario;

import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

public interface UserUpdater {
    UserResponse update(long userId, UserRequest model);
}
