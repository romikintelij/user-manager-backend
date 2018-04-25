package zab.romik.usermanagement.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usecases.repository.UsersRepository;
import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;
import zab.romik.usermanagement.usecases.scenario.Registration;
import zab.romik.usermanagement.usecases.scenario.UserUpdater;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class UserFacadeImpl implements UserFacade {
    private final Registration registration;
    private final UserUpdater userUpdater;
    private final UsersRepository usersRepository;

    public UserFacadeImpl(Registration registration, UserUpdater userUpdater,
                          UsersRepository usersRepository) {
        this.registration = registration;
        this.userUpdater = userUpdater;
        this.usersRepository = usersRepository;
    }

    @Override
    public UserResponse register(UserRequest newUser) {
        return registration.register(newUser);
    }

    @Override
    public UserResponse update(long userId, UserRequest data) {
        return userUpdater.update(userId, data);
    }

    @Override
    public List<UserResponse> loadAllUsers() {
        return usersRepository.findAll().stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }
}
