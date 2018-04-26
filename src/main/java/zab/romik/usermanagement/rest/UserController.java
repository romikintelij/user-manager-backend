package zab.romik.usermanagement.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import zab.romik.usermanagement.usermanagement.users.UserService;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Этот контроллер создан для того чтобы обеспечить интерфейс над
 * бизнес правилами которые работают с пользователем
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * @param userService сервис для работы с пользователями
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Этот метод создает нового пользователя в системе и возвращает информацию
     * по только что созданному пользователю
     *
     * @param req форма с данными о новом пользователе
     * @return созданный пользователь
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel create(@Valid @RequestBody NewUser req) {
        return userService.create(req);
    }

    /**
     * Загружает пользователя по id.
     * <p>
     * Этот метод выбрасывает исключение, когда пользователя по id загрузить
     * не удалось, он не существует
     *
     * @param id пользователя которого надо загрузить
     * @return представление найденного пользователя
     */
    @GetMapping("/{id}")
    public UserModel fetchById(@PathVariable long id) {
        return userService.loadById(id);
    }

    /**
     * Загружает всех пользователей в системе
     * <p>
     * Этот метод работает без разбиения по страницам, это можно добавить
     * в будущем
     *
     * @return коллекция со всеми пользователями в системе
     */
    @GetMapping
    public Collection<UserModel> findAllUsers() {
        return userService.loadAllUsers();
    }

    /**
     * Метод должен удалять пользователя в системе
     * <p>
     * Этот метод может выбросить исключение, когда пользователь для удаления
     * небыл найден
     *
     * @param id пользователя которого надо удалить
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.delete(id);
    }

    /**
     * Обновляет информацию о пользователе в системе
     * <p>
     * Может выбросить исключение если пользователь с переданными id не был
     * найден в базе данных
     *
     * @param id  пользователя которого нужно обновить
     * @param req модель с данными которые нужно обновить
     * @return обновленная модель пользователя
     */
    @PutMapping("/{id}")
    public UserModel update(@PathVariable long id, @Valid @RequestBody NewUser req) {
        return userService.update(id, req);
    }
}
