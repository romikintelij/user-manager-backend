package zab.romik.usermanagement.usermanagement.users;

import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.exception.UserDuplicateException;
import zab.romik.usermanagement.usermanagement.users.exception.UserNotFoundException;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.Collection;
import java.util.Set;

/**
 *  simple crud service that works with user data
 */
public interface UserService {

    /**
     * create new user in a system
     * <p>
     * This method checks the user for uniqueness if the user
     * with the same login in the system already exists, an exception will be thrown
     * about duplicate login
     *
     * @param userModel user model which is supplemented with a password
     * @return created user, view
     * @throws UserDuplicateException when there is a user with the same login
     */
    UserModel create(NewUser userModel);

    /**
     * Loads a user by id, can throw an exception if
     * user with the passed id was not found
     *
     * @param id whose user you want to download
     * @return found user
     * @throws UserNotFoundException when the user was not found
     */
    UserModel loadById(long id);

    /**
     * updates the user in the system
     * <p>
     * method can throw an exception if the user you want does not exist
     * found
     *
     * @param userId user ID of which  want to edit
     * @param model  the model we will roll on the user
     * @return saved user
     * @throws UserNotFoundException when the user with the passed id does not exist
     */
    UserModel update(long userId, NewUser model);

    /**
     * Deletes the user with the passed id
     *
     * Can throw an exception when the user with the passed id does not exist
     *
     * @param id user for deletion
     * @throws UserNotFoundException user with the transmitted id in the system is not present
     */
    void delete(long id);

    /**
     * loads a collection of all users in the system
     *
     */
    Collection<UserModel> loadAllUsers();

    /**
     *
     * loads a collection of all groups in the system
     */
    Collection<GroupModel> fetchUserGroups(long userId);
}
