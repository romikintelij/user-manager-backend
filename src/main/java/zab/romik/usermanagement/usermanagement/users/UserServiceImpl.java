package zab.romik.usermanagement.usermanagement.users;

import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usermanagement.groups.Groups;
import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.domain.Credentials;
import zab.romik.usermanagement.usermanagement.users.domain.PersonalDetails;
import zab.romik.usermanagement.usermanagement.users.domain.User;
import zab.romik.usermanagement.usermanagement.users.exception.UserDuplicateException;
import zab.romik.usermanagement.usermanagement.users.exception.UserNotFoundException;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * service works with user data, simple crud operations
 */
@Transactional
public class UserServiceImpl implements UserService {
    private final Users users;
    private final Groups groups;
    private final PasswordEncoder pwdEncoder;

    /**
     * @param users interface for accessing user data
     */
    public UserServiceImpl(Users users, Groups groups,
                           PasswordEncoder pwdEncoder) {
        this.users = users;
        this.groups = groups;
        this.pwdEncoder = pwdEncoder;
    }

    /**
     * make create new user
     * @param userModel
     * @return new user model
     */
    @Override
    public UserModel create(NewUser userModel) {
        assertUniqueUserName(userModel.getUsername());

        Credentials credentials = createCredentials(userModel);
        User user = new User(credentials);

        return new UserModel(commonUserPersist(user, userModel));
    }

    /**
     * auxiliary method to create an object with authorization data
     * for the user
     *
     * @param user user model from which we need to get login and password
     * @return representation of data for authorization
     */
    private Credentials createCredentials(NewUser user) {
        String hashedPassword = pwdEncoder.encode(user.getPassword());

        return new Credentials(user.getUsername(), hashedPassword);
    }

    /**
     * Method for creating an object that represents the user's personal data
     *
     * @param userModel a model with data from which we will fill the model
     *                    with personal data
     * @return representation of personal data
     */
    private PersonalDetails createPersonalDetails(UserModel userModel) {
        return new PersonalDetails(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getDateOfBirth()
        );
    }

    /**
     * This method checks that the transmitted user name is present in a single instance in the system.
     *
     * @param username username we need to check
     */
    private void assertUniqueUserName(String username) {
        users.findByUsername(username).ifPresent(this::throwDuplicateException);
    }

    /**
     * auxiliary method that throws an exception that the user
     * already exists in the system
     *
     * @param user existing user
     */
    private void throwDuplicateException(User user) {
        Credentials credentials = user.getCredentials();

        throw new UserDuplicateException(credentials.getUsername());
    }

    /** make create new user*/
    @Override
    public UserModel loadById(long id) {
        return new UserModel(obtainUserById(id));
    }

    /**
     *  method provides the ability to get the user by id
     * <p>
     *  will throw an exception when the user is not id
     *  found in database
     *
     * @param id user id of which we need to get
     * @return found user
     */
    private User obtainUserById(long id) {
        return users.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserModel update(long userId, NewUser model) {
        User user = obtainUserById(userId);
        Credentials credentials = makeNewCredentialsWithUpdate(user.getCredentials(), model);

        user.setCredentials(credentials);

        return new UserModel(commonUserPersist(user, model));
    }

    /**
     * This method updates authorization data in the user model
     *
     * @param source source class
     * @param req    user request
     * @return updated authorization data
     */
    private Credentials makeNewCredentialsWithUpdate(Credentials source, NewUser req) {
        Credentials credentials = new Credentials(source);

        String reqUserName = req.getUsername().trim();
        if (!credentials.getUsername().equals(reqUserName)) {
            credentials.setUsername(req.getUsername());
        }

        String pwd = req.getPassword();
        if (!(pwd == null || !pwd.isEmpty())) {
            credentials.setPassword(pwdEncoder.encode(pwd));
        }

        return credentials;
    }

    /**
     * Повторяющийся код сохранения, сохраняет модель пользователя с заполнением
     * модели персональными данными и привязкой к группам
     *
     * @param model     сущность пользователя
     * @param userModel данные пользователя из запроса
     * @return сущность пользователя сохраненная в системе
     */
    private User commonUserPersist(User model, NewUser userModel) {
        PersonalDetails personalDetails = createPersonalDetails(userModel);
        model.setPersonalDetails(personalDetails);

        return users.save(linkToGroups(model, userModel.getGroupIds()));
    }

    /**
     * Binds the user to the groups that were sent along with the user
     *
     * @param user
     * @param groupIds groups that need to be bound to the user
     * @return user with attached groups
     */
    private User linkToGroups(User user, Set<Long> groupIds) {
        Set<Group> foundedGroups = groups.findAllByIdIn(groupIds);
        if (foundedGroups.isEmpty()) {
            return user;
        }

        user.addToGroups(foundedGroups);

        return user;
    }

    /**the method make delete user by id of database*/
    @Override
    public void delete(long id) {
        users.delete(obtainUserById(id));
    }

    /** the method loading all user of database*/
    @Override
    public Collection<UserModel> loadAllUsers() {
        return users.findAll().stream().map(UserModel::new).collect(Collectors.toList());
    }

    /**the method receives all user groups*/
    @Override
    public Collection<GroupModel> fetchUserGroups(long userId) {
        User user = obtainUserById(userId);

        return convertUserGroupsToGroupModel(user.getGroups());
    }

    /**
     * This method converts a set of groups into a set of group representations
     *
         * @param groups multiple input groups
     * @return collection of models of groups
     */
    private Collection<GroupModel> convertUserGroupsToGroupModel(Set<Group> groups) {
        return groups.stream().map(GroupModel::new).collect(Collectors.toList());
    }
}
