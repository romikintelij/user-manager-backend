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
import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.GroupService;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;

import javax.validation.Valid;
import java.util.List;

/**
 * controller for working with groups
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    /**
     * group service
     */
    private final GroupService groupService;

    /**
     * class constructor
     * @param groupService
     */
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     *
     * @return all groups of a system
     */
    @GetMapping
    public List<GroupModel> findAllGroups() {
        return groupService.findAllGroups();
    }

    /**
     *
     * @param id
     * @return chosen group by id of a system
     */
    @GetMapping("/{id}")
    public GroupModel findGroupById(@PathVariable long id) {
        return groupService.findById(id);
    }

    /**
     *  make deleting chosen group of a system
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        groupService.delete(id);
    }

    /**
     * method make create group moodel
     * @param req
     * @return save group in a system
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel create(@RequestBody @Valid GroupModel req) {
        return groupService.create(req);
    }

    /**
     * method make update group model
     * @param id
     * @param req
     * @return save an updated group in a system
     */
    @PutMapping("/{id}")
    public GroupModel update(@PathVariable long id, GroupModel req) {
        return groupService.update(id, req);
    }
}
