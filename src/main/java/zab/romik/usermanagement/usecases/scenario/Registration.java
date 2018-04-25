package zab.romik.usermanagement.usecases.scenario;

import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

public interface Registration {
    UserResponse register(UserRequest userRequest);
}
