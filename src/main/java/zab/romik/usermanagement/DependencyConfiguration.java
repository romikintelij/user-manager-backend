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
 * Наша архитектура практически не зависима от фреймворка, по-этому мы не
 * используем аннотации которые предоставляют возможности для работы с DI
 *
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see org.springframework.stereotype.Component
 * @see org.springframework.stereotype.Service
 */
@Configuration
public class DependencyConfiguration {

    /**
     * Этот метод создает сервис для работы с пользователями который доступен для
     * внедрения на внешнем уровне архитектуры
     *
     * @param springJpaUsers   реализация интерфейса которые требует бизнес логика
     * @param groupsRepository репозиторий для работы с группами
     * @return сервис для работы с пользователями
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
