package zab.romik.usermanagement.usermanagement.groups;

import org.springframework.transaction.annotation.Transactional;
import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
public class GroupServiceImpl implements GroupService {
    private final Groups groups;

    public GroupServiceImpl(Groups groups) {
        this.groups = groups;
    }

    @Override
    public GroupModel create(GroupModel model) {
        var group = groups.save(new Group(model.getName()));

        return new GroupModel(group);
    }

    @Override
    public void delete(long id) {
        groups.delete(obtainGroup(id));
    }

    private Group obtainGroup(long id) {
        return groups.findById(id).orElseThrow();
    }

    @Override
    public GroupModel update(long id, GroupModel model) {
        var group = obtainGroup(id);
        group.setName(model.getName());

        return new GroupModel(groups.save(group));
    }

    @Override
    public List<GroupModel> findAllGroups() {
        return convertCollectionTo(groups.findAll(), GroupModel::new);
    }

    @Override
    public GroupModel findById(long id) {
        return new GroupModel(obtainGroup(id));
    }

    @Override
    public List<UserModel> findUsersInGroup(long groupId) {
        return convertCollectionTo(obtainGroup(groupId).getUsers(), UserModel::new);
    }

    private <R, I> List<R> convertCollectionTo(Collection<I> input, Function<I, R> mapper) {
        return input.stream().map(mapper).collect(Collectors.toList());
    }
}
