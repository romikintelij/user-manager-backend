package zab.romik.usermanagement.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.UserService;

import java.util.Collection;

/**
 *
 *controller for working with groups belonging to a user
 */
@RestController
@RequestMapping("/users/{id}/groups")
public class UserGroupsController {

    /**
     * service for working with a users
     */
    private final UserService userService;

    /**
     * class connstructor
     * @param userService
     */
    public UserGroupsController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param id
     * @return groups belonging to a user
     */
    @GetMapping
    public Collection<GroupModel> getUserGroups(@PathVariable long id) {
        return userService.fetchUserGroups(id);
    }
}
