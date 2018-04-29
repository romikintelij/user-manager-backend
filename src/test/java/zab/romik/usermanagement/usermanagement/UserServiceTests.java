package zab.romik.usermanagement.usermanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.users.UserService;
import zab.romik.usermanagement.usermanagement.users.domain.Credentials;
import zab.romik.usermanagement.usermanagement.users.domain.PersonalDetails;
import zab.romik.usermanagement.usermanagement.users.domain.User;
import zab.romik.usermanagement.usermanagement.users.exception.UserDuplicateException;
import zab.romik.usermanagement.usermanagement.users.exception.UserNotFoundException;
import zab.romik.usermanagement.usermanagement.users.model.NewUser;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@AutoConfigureCache
public class UserServiceTests {
    private static final String FAKE_GROUP_NAME = "developers";
    private static final String TEST_USERNAME = "test-user";
    private static final String TEST_USER_PASSWORD = "sb201323";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserService userService;

    private NewUser fixtureUser;

    @Before
    public void setUp() {
        long groupId = testEntityManager.persist(new Group(FAKE_GROUP_NAME)).getId();

        NewUser newUserReq = new NewUser();
        newUserReq.setUsername(TEST_USERNAME);
        newUserReq.setPassword(TEST_USER_PASSWORD);
        newUserReq.setDateOfBirth(LocalDate.now());
        newUserReq.setFirstName("John");
        newUserReq.setLastName("Doe");

        Set<Long> groupIds = Collections.singleton(groupId);
        newUserReq.setGroupIds(groupIds);

        this.fixtureUser = newUserReq;
    }

    @Test
    public void testCreateNewUser() {
        UserModel createdUser = userService.create(fixtureUser);

        Assert.assertNotNull(createdUser);
        Assert.assertTrue(createdUser.getId() > 0);

        // check that user really persisted
        User user = testEntityManager.find(User.class, createdUser.getId());

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getCredentials().getUsername(), TEST_USERNAME);
    }

    @Test(expected = UserDuplicateException.class)
    public void testUniqueUserNameException() {
        String username = fixtureUser.getUsername();

        User user = new User(new Credentials(username, "abcd"));
        user.setPersonalDetails(new PersonalDetails("integration", "test", LocalDate.now()));

        // create user with username from fixture user
        testEntityManager.persist(user);

        userService.create(fixtureUser);
    }

    @Test
    public void testPasswordEncoding() {
        UserModel created = userService.create(fixtureUser);

        // paranoid check
        Assert.assertNotNull(created);

        User user = testEntityManager.find(User.class, created.getId());
        Credentials credentials = user.getCredentials();

        Assert.assertEquals(credentials.getUsername(), TEST_USERNAME);
        Assert.assertNotEquals(credentials.getPassword(), TEST_USER_PASSWORD);

        // encoded password length
        Assert.assertTrue(credentials.getPassword().length() > TEST_USER_PASSWORD.length());
    }

    @Test(expected = UserNotFoundException.class)
    public void testExceptionWhenLoadNotFoundUser() {
        userService.loadById(Integer.MAX_VALUE);
    }

    @Test
    public void testFindAlreadyExistedUser() {
        UserModel created = userService.create(fixtureUser);
        UserModel founded = userService.loadById(created.getId());

        // we should have same users
        Assert.assertEquals(created.getId(), founded.getId());
        Assert.assertEquals(created.getUsername(), founded.getUsername());
    }

    @Test
    public void testDeleting() {
        long userId = userService.create(fixtureUser).getId();

        userService.delete(userId);

        // check that user was deleted
        User userFromDb = testEntityManager.find(User.class, userId);
        Assert.assertNull(userFromDb);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeletedNotFoundUser() {
        userService.delete(Integer.MAX_VALUE);
    }

    @Test
    public void testThatUserLinkedToGroups() {
        long createdUserId = userService.create(fixtureUser).getId();

        User user = testEntityManager.find(User.class, createdUserId);

        Assert.assertEquals(user.getGroups().size(), fixtureUser.getGroupIds().size());
        Assert.assertEquals(user.getGroups().iterator().next().getName(), FAKE_GROUP_NAME);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateWithNotFoundUser() {
        userService.update(Integer.MAX_VALUE, fixtureUser);
    }

    @Test
    public void testHappyUpdateCase() {
        UserModel created = userService.create(fixtureUser);

        NewUser newData = new NewUser();
        newData.setFirstName("Adam");
        // because username is required
        newData.setUsername(TEST_USERNAME);

        UserModel updated = userService.update(created.getId(), newData);
        // check that returned response contains new data
        Assert.assertEquals(updated.getFirstName(), newData.getFirstName());

        User userInDb = testEntityManager.find(User.class, updated.getId());
        Assert.assertEquals(userInDb.getPersonalDetails().getFirstName(), updated.getFirstName());
    }

    // todo: add test for check update username and password
}
