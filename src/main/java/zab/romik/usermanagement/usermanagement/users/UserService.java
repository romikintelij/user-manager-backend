package zab.romik.usermanagement.usermanagement.users;

import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.exception.UserDuplicateException;
import zab.romik.usermanagement.usermanagement.users.exception.UserNotFoundException;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.Collection;
import java.util.Set;

/**
 * Это простой crud сервис который работает с данными пользователя
 */
public interface UserService {

    /**
     * Создать нового пользователя в системе.
     * <p>
     * Этот метод проверяет пользователя на уникальность, если пользователь
     * с таким же логином в системе уже существует, то будет выброшено исключение
     * о дублированном логине
     *
     * @param userModel модель пользователя которая дополнена паролем
     * @return созданный пользователь, представление
     * @throws UserDuplicateException когда есть пользователь с таким же логином
     */
    UserModel create(NewUser userModel);

    /**
     * Загружает пользователя по id, может выбросить исключение, если
     * пользователь с переданным id не был найден
     *
     * @param id пользователя которого нужно загрузить
     * @return найденный пользователь
     * @throws UserNotFoundException когда пользователя найти не удалось
     */
    UserModel loadById(long id);

    /**
     * Обновляет пользователя в системе
     * <p>
     * Этот метод может выбросить исключение если нужный пользователь не будет
     * найден
     *
     * @param userId идентификатор пользователя которого хотим отредактировать
     * @param model  модель которую будем накатывать на пользователя
     * @return сохраненный пользователь
     * @throws UserNotFoundException когда пользователя с переданным id не существует
     */
    UserModel update(long userId, NewUser model);

    /**
     * Удаляет пользователь с переданным id
     *
     * Может выбросить исключение когда пользователя с переданным id не существует,
     * это может значить, что пользователя либо никогда не существовало, либо пользователь
     * уже был удален
     *
     * @param id пользователя для удаления
     * @throws UserNotFoundException пользователя с переданным id в системе нет
     */
    void delete(long id);

    /**
     * Загружает коллекцию всех пользователей в системе
     *
     * В будущем можно добавить метод с пагинацией
     *
     * @return коллекция пользователей в системе, никогда не возвращается
     * {@code null}
     */
    Collection<UserModel> loadAllUsers();

    Collection<GroupModel> fetchUserGroups(long userId);
}
