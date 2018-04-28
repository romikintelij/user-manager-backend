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

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupModel> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/{id}")
    public GroupModel findGroupById(@PathVariable long id) {
        return groupService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        groupService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel create(@RequestBody @Valid GroupModel req) {
        return groupService.create(req);
    }

    @PutMapping("/{id}")
    public GroupModel update(@PathVariable long id, GroupModel req) {
        return groupService.update(id, req);
    }
}
