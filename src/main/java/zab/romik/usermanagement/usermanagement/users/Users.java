package zab.romik.usermanagement.usermanagement.users;

import zab.romik.usermanagement.usermanagement.users.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Этот интерфейс должен использоваться для доступа к данным. В пакете бизнес
 * логики этот интерфейс реализовываться не должен, потому что наш слой бизнес
 * логики не должен зависить от баз данных и интерфейсов фреймворка, это у нас
 * делается на первом уровне архитектуры
 */
public interface Users {
    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    void delete(User user);

    User save(User user);

    List<User> findAll();
}
