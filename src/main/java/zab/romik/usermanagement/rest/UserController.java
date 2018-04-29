package zab.romik.usermanagement.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import zab.romik.usermanagement.usermanagement.users.model.ValidationGroups;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import javax.validation.Valid;
import java.util.Collection;

/**
 * controller for working with user
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * @param userService service for working with user
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * creating new user
     * @param req  form with new user data
     * @return created user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel create(@Validated(ValidationGroups.Create.class) @RequestBody NewUser req) {
        return userService.create(req);
    }

    /**
     * loading user by id
     * <p>
     * method throws an exception when chosen user is not exist
     *
     *@param id
     * @return view of the found user
     */
    @GetMapping("/{id}")
    public UserModel fetchById(@PathVariable long id) {
        return userService.loadById(id);
    }

    /**
     * loading all user in a system
     *
     * @return users list
     */
    @GetMapping
    public Collection<UserModel> findAllUsers() {
        return userService.loadAllUsers();
    }

    /**
     * method make delete user of a system
     * can throws an exception when chosen user is not exist
     * @param id  of the found user  which must be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.delete(id);
    }

    /**
     * make update infarmation about a user
     * <p>
     * can throws an exception when chosen user is not exist in a data base
     *
     * @param id  of user which needs to be updated
     * @param req model with data which needs to be updated
     * @return updated model of user
     */
    @PutMapping("/{id}")
    public UserModel update(@PathVariable long id, @Valid @RequestBody NewUser req) {
        return userService.update(id, req);
    }
}
