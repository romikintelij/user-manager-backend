package zab.romik.usermanagement.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zab.romik.usermanagement.rest.request.UserGroups;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.UserService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/users/{id}/groups")
public class UserGroupsController {
    private final UserService userService;

    public UserGroupsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<GroupModel> getUserGroups(@PathVariable long id) {
        return userService.fetchUserGroups(id);
    }

    @PutMapping
    public Collection<GroupModel> putUserGroups(@PathVariable long id,
                                                @RequestBody @Valid UserGroups req) {
        return userService.linkUserToGroups(id, req.getGroupIds());
    }
}
