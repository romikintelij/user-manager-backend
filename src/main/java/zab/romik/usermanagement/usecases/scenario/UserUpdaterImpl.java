package zab.romik.usermanagement.usecases.scenario;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usecases.domain.PersonalDetails;
import zab.romik.usermanagement.usecases.exception.UserNotFound;
import zab.romik.usermanagement.usecases.repository.UsersRepository;
import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

@Component
public class UserUpdaterImpl implements UserUpdater {
    private final UsersRepository usersRepository;
    private final UserGroups groups;

    public UserUpdaterImpl(UsersRepository usersRepository,
                           UserGroups groups) {
        this.usersRepository = usersRepository;
        this.groups = groups;
    }

    @Override
    public UserResponse update(long userId, UserRequest model) {
        var user = usersRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));

        // personal details is immutable value-object
        var personalDetails = user.getPersonalDetails()
                .setFirstName(model.getFirstName())
                .setLastName(model.getLastName())
                .setDateOfBirth(model.getDateOfBirth());

        user.setPersonalDetails(personalDetails);
        usersRepository.save(user);

        // set new user groups if need
        groups.linkUserToGroups(userId, model.getGroupIds());

        return UserResponse.of(user);
    }
}
