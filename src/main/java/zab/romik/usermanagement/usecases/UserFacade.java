package zab.romik.usermanagement.usecases;

import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

import java.util.List;

public interface UserFacade {
    UserResponse register(UserRequest newUser);

    UserResponse update(long userId, UserRequest data);

    List<UserResponse> loadAllUsers();
}
