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
import zab.romik.usermanagement.usecases.GroupsFacade;
import zab.romik.usermanagement.usecases.request.GroupRequest;
import zab.romik.usermanagement.usecases.response.GroupResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    private final GroupsFacade groupsFacade;

    public GroupsController(GroupsFacade groupsFacade) {
        this.groupsFacade = groupsFacade;
    }

    /**
     * Метод создает новую группу в системе в которую потом будет
     * возможно добавлять пользователей
     *
     * @param req запрос на добавление новой группы
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupResponse create(@RequestBody @Valid GroupRequest req) {
        return groupsFacade.create(req);
    }

    /**
     * Загружает все группы что есть в системе
     *
     * @return список групп которые есть в системе
     */
    @GetMapping
    public List<GroupResponse> loadAllGroups() {
        // in future can add pagination
        return groupsFacade.loadAllGroups();
    }

    /**
     * Удалить группу из системы
     *
     * @param id удаляемой группы
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        groupsFacade.delete(id);
    }

    /**
     * Обновляет группу по ее идентификатору в системе
     *
     * @param id группы которую будем обновлять
     * @param groupRequest запрос на изменение группы
     */
    @PutMapping("/{id}")
    public GroupResponse update(@PathVariable long id, @RequestBody @Valid GroupRequest groupRequest) {
        return groupsFacade.update(id, groupRequest);
    }
}
