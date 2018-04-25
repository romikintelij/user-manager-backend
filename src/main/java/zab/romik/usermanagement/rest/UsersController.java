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
import zab.romik.usermanagement.usecases.UserFacade;
import zab.romik.usermanagement.usecases.repository.UsersRepository;
import zab.romik.usermanagement.usecases.request.UserRequest;
import zab.romik.usermanagement.usecases.response.UserResponse;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for work with users
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserFacade userFacade;
    private final UsersRepository usersRepository;

    public UsersController(UserFacade userFacade, UsersRepository usersRepository) {
        this.userFacade = userFacade;
        this.usersRepository = usersRepository;
    }

    /**
     * Create new user in system
     *
     * @param req user request, should be valid
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserRequest req) {
        return userFacade.register(req);
    }

    /**
     * Удаляет пользователя из системы по id этого пользователя
     *
     * @param id пользователя которого нужно удалить
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        usersRepository.deleteById(id);
    }

    /**
     * Возвращает список пользователей с навигацией по ним
     *
     * @return пользователи которые есть в системе
     */
    @GetMapping
    public List<UserResponse> list() {
        return userFacade.loadAllUsers();
    }

    /**
     * Обновляет пользователя по переданному id, может вернуть
     * 404 если пользователя найти не удалось по переданному id
     *
     * @param id пользователя которого нужно обновить
     * @return обновленный пользователь
     */
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable long id, @RequestBody @Valid UserRequest request) {
        return userFacade.update(id, request);
    }
}
