package zab.romik.usermanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zab.romik.usermanagement.data.GroupsRepository;
import zab.romik.usermanagement.data.UsersRepository;
import zab.romik.usermanagement.security.Sha256PasswordEncoder;
import zab.romik.usermanagement.usermanagement.groups.GroupService;
import zab.romik.usermanagement.usermanagement.groups.GroupServiceImpl;
import zab.romik.usermanagement.usermanagement.users.UserService;
import zab.romik.usermanagement.usermanagement.users.UserServiceImpl;

/**
 * Our architecture is almost independent of the framework, so we do not
 * use annotations that provide opportunities for working with DI
 *
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see org.springframework.stereotype.Component
 * @see org.springframework.stereotype.Service
 */
@Configuration
public class DependencyConfiguration {

    /**
     *
     * This method creates a service for work with users that is available for
     * implementation on the external level of architecture
     *
     * @param springJpaUsers   implementation of an interface that requires business logic
     * @param groupsRepository repository for working with groups
     * @return service for work with users
     */
    @Bean
    public UserService userService(UsersRepository springJpaUsers,
                                   GroupsRepository groupsRepository,
                                   Sha256PasswordEncoder passwordEncoder) {

        return new UserServiceImpl(springJpaUsers, groupsRepository, passwordEncoder);
    }

    @Bean
    public GroupService groupService(GroupsRepository groupsRepository) {
        return new GroupServiceImpl(groupsRepository);
    }
}
