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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис работает с данными пользователя, простые crud операции
 */
@Transactional
public class UserServiceImpl implements UserService {
    private final Users users;
    private final Groups groups;
    private final PasswordEncoder pwdEncoder;

    /**
     * @param users интерфейс для доступа к данным пользователя
     */
    public UserServiceImpl(Users users, Groups groups,
                           PasswordEncoder pwdEncoder) {
        this.users = users;
        this.groups = groups;
        this.pwdEncoder = pwdEncoder;
    }

    @Override
    public UserModel create(NewUser userModel) {
        assertUniqueUserName(userModel.getUsername());

        var credentials = createCredentials(userModel);
        var user = new User(credentials);

        return new UserModel(commonUserPersist(user, userModel));
    }

    /**
     * Вспомогательный метод для того чтобы создать объект с данными авторизации
     * для пользователя
     *
     * @param user модель пользователя из которой мы должны достать логин и пароль
     * @return представление данных для авторизации
     */
    private Credentials createCredentials(NewUser user) {
        var hashedPassword = pwdEncoder.encode(user.getPassword());

        return new Credentials(user.getUsername(), hashedPassword);
    }

    /**
     * Метод для создания объекта который представляет персональные данные пользователя
     *
     * @param userModel модель с данными из которых мы будем заполнять модель
     *                  с персональными данными
     * @return представление персональных данных
     */
    private PersonalDetails createPersonalDetails(UserModel userModel) {
        return new PersonalDetails(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getDateOfBirth()
        );
    }

    /**
     * Этот метод проверяет что переданное имя пользователя присутствует в единственном
     * экземпляре в системе.
     *
     * @param username имя пользователя которое мы должны проверить
     */
    private void assertUniqueUserName(String username) {
        users.findByUsername(username).ifPresent(this::throwDuplicateException);
    }

    /**
     * Вспомогательный метод который создает исключение о том, что пользователь
     * уже существует в системе
     *
     * @param user существующий пользователь
     */
    private void throwDuplicateException(User user) {
        var credentials = user.getCredentials();

        throw new UserDuplicateException(credentials.getUsername());
    }

    @Override
    public UserModel loadById(long id) {
        return new UserModel(obtainUserById(id));
    }

    /**
     * Метод предоставляет возможность получить пользователя по id
     * <p>
     * Этот метод будет выбрасывать исключение когда пользователь по id не был
     * найден в базе данных
     *
     * @param id пользователя которого нам надо получить
     * @return найденный пользователь
     */
    private User obtainUserById(long id) {
        return users.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserModel update(long userId, NewUser model) {
        var user = obtainUserById(userId);
        var credentials = makeNewCredentialsWithUpdate(user.getCredentials(), model);

        user.setCredentials(credentials);

        return new UserModel(commonUserPersist(user, model));
    }

    /**
     * Этот метод просто обновляет данные авторизации в моделе пользователя
     *
     * @param source исходный класс
     * @param req    запрос пользователя
     * @return обновленные данные авторизации
     */
    private Credentials makeNewCredentialsWithUpdate(Credentials source, NewUser req) {
        var credentials = new Credentials(source);

        var reqUserName = req.getUsername().trim();
        if (!credentials.getUsername().equals(reqUserName)) {
            credentials.setUsername(req.getUsername());
        }

        var pwd = req.getPassword();
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
        var personalDetails = createPersonalDetails(userModel);
        model.setPersonalDetails(personalDetails);

        return users.save(linkToGroups(model, userModel.getGroupIds()));
    }

    /**
     * Привязывает пользователя к группам которые были переданы вместе
     * с пользователем
     *
     * @param user     пользователь
     * @param groupIds группы которые надо привязать к пользователю
     * @return пользователь с привязанными группами
     */
    private User linkToGroups(User user, Set<Long> groupIds) {
        var foundedGroups = groups.findAllByIdIn(groupIds);
        if (foundedGroups.isEmpty()) {
            return user;
        }

        user.addToGroups(foundedGroups);

        return user;
    }

    @Override
    public void delete(long id) {
        users.delete(obtainUserById(id));
    }

    @Override
    public Collection<UserModel> loadAllUsers() {
        return users.findAll().stream().map(UserModel::new).collect(Collectors.toList());
    }

    @Override
    public Collection<GroupModel> fetchUserGroups(long userId) {
        var user = obtainUserById(userId);

        return convertUserGroupsToGroupModel(user.getGroups());
    }

    /**
     * Этот метод просто конвертирует множество групп в множество представлений групп
     *
     * @param groups множество входных групп
     * @return коллекция моделей групп
     */
    private Collection<GroupModel> convertUserGroupsToGroupModel(Set<Group> groups) {
        return groups.stream().map(GroupModel::new).collect(Collectors.toList());
    }
}
