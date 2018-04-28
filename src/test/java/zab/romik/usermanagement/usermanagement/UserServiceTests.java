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

import java.time.LocalDate;
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
        var groupId = testEntityManager.persist(new Group(FAKE_GROUP_NAME)).getId();

        var newUserReq = new NewUser();
        newUserReq.setUsername(TEST_USERNAME);
        newUserReq.setPassword(TEST_USER_PASSWORD);
        newUserReq.setDateOfBirth(LocalDate.now());
        newUserReq.setFirstName("John");
        newUserReq.setLastName("Doe");
        newUserReq.setGroupIds(Set.of(groupId));

        this.fixtureUser = newUserReq;
    }

    @Test
    public void testCreateNewUser() {
        var createdUser = userService.create(fixtureUser);

        Assert.assertNotNull(createdUser);
        Assert.assertTrue(createdUser.getId() > 0);

        // check that user really persisted
        var user = testEntityManager.find(User.class, createdUser.getId());

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getCredentials().getUsername(), TEST_USERNAME);
    }

    @Test(expected = UserDuplicateException.class)
    public void testUniqueUserNameException() {
        var username = fixtureUser.getUsername();

        var user = new User(new Credentials(username, "abcd"));
        user.setPersonalDetails(new PersonalDetails("integration", "test", LocalDate.now()));

        // create user with username from fixture user
        testEntityManager.persist(user);

        userService.create(fixtureUser);
    }

    @Test
    public void testPasswordEncoding() {
        var created = userService.create(fixtureUser);

        // paranoid check
        Assert.assertNotNull(created);

        var user = testEntityManager.find(User.class, created.getId());
        var credentials = user.getCredentials();

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
        var created = userService.create(fixtureUser);
        var founded = userService.loadById(created.getId());

        // we should have same users
        Assert.assertEquals(created.getId(), founded.getId());
        Assert.assertEquals(created.getUsername(), founded.getUsername());
    }

    @Test
    public void testDeleting() {
        var userId = userService.create(fixtureUser).getId();

        userService.delete(userId);

        // check that user was deleted
        var userFromDb = testEntityManager.find(User.class, userId);
        Assert.assertNull(userFromDb);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeletedNotFoundUser() {
        userService.delete(Integer.MAX_VALUE);
    }

    @Test
    public void testThatUserLinkedToGroups() {
        var createdUserId = userService.create(fixtureUser).getId();

        var user = testEntityManager.find(User.class, createdUserId);

        Assert.assertEquals(user.getGroups().size(), fixtureUser.getGroupIds().size());
        Assert.assertEquals(user.getGroups().iterator().next().getName(), FAKE_GROUP_NAME);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateWithNotFoundUser() {
        userService.update(Integer.MAX_VALUE, fixtureUser);
    }

    @Test
    public void testHappyUpdateCase() {
        var created = userService.create(fixtureUser);

        var newData = new NewUser();
        newData.setFirstName("Adam");
        // because username is required
        newData.setUsername(TEST_USERNAME);

        var updated = userService.update(created.getId(), newData);
        // check that returned response contains new data
        Assert.assertEquals(updated.getFirstName(), newData.getFirstName());

        var userInDb = testEntityManager.find(User.class, updated.getId());
        Assert.assertEquals(userInDb.getPersonalDetails().getFirstName(), updated.getFirstName());
    }

    // todo: add test for check update username and password
}
