package zab.romik.usermanagement.usecases.scenario;

import org.springframework.stereotype.Component;
import zab.romik.usermanagement.usecases.domain.Credentials;
import zab.romik.usermanagement.usecases.domain.PersonalDetails;
import zab.romik.usermanagement.usecases.domain.User;
import zab.romik.usermanagement.usecases.exception.UserAlreadyExist;
import zab.romik.usermanagement.usecases.inversioncontrol.PasswordEncrypter;
import zab.romik.usermanagement.usecases.repository.UsersRepository;
import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

import java.util.Objects;

/**
 * This class implement registration use case
 */
@Component
public class RegistrationImpl implements Registration {
    private final UsersRepository usersRepository;
    private final PasswordEncrypter passwordEncoder;
    private final UserGroups userGroups;

    public RegistrationImpl(UsersRepository usersRepository,
                            PasswordEncrypter passwordEncoder,
                            UserGroups userGroups) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userGroups = userGroups;
    }

    @Override
    public UserResponse register(UserRequest request) {
        Objects.requireNonNull(request);

        assertUserNameIsUnique(request.getUsername());

        var user = makeUserEntity(request);
        usersRepository.saveAndFlush(user);

        userGroups.linkUserToGroups(user.getId(), request.getGroupIds());

        return UserResponse.of(user);
    }

    /**
     * Assert that in db exist only one user with given username
     *
     * @param username of new user
     */
    private void assertUserNameIsUnique(String username) {
        usersRepository.findByCredentialsUsername(username)
                .ifPresent(this::throwUserDuplicateException);
    }

    /**
     * Throw exception about duplicated user name
     *
     * @param user duplicated user
     */
    private void throwUserDuplicateException(User user) {
        throw new UserAlreadyExist(user.getUsername());
    }

    /**
     * Method for create user entity, preparing for persisting
     *
     * @param req new user request
     * @return user entity
     */
    private User makeUserEntity(UserRequest req) {
        var personalDetails = PersonalDetails.of(req.getFirstName(),
                req.getLastName(), req.getDateOfBirth());

        return new User(makeCredentials(req), personalDetails);
    }

    /**
     * This method should create credentials object for
     * user. Required for authentication
     *
     * @param req user request
     * @return credentials object
     */
    private Credentials makeCredentials(UserRequest req) {
        return new Credentials(req.getUsername(),
                passwordEncoder.encode(req.getPassword()));
    }
}
