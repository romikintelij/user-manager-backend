package zab.romik.usermanagement.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zab.romik.usermanagement.usermanagement.groups.GroupService;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.Collection;

@RestController
@RequestMapping("/groups/{id}")
public class GroupUsersController {
    private final GroupService groupService;

    public GroupUsersController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/users")
    public Collection<UserModel> loadUsersInGroup(@PathVariable long id) {
        return groupService.findUsersInGroup(id);
    }
}
