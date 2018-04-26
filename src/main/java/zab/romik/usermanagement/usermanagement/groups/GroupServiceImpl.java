package zab.romik.usermanagement.usermanagement.groups;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;

import java.util.List;

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
    public List<Group> findAllGroups() {
        return groups.findAll();
    }

    @Override
    public GroupModel findById(long id) {
        return new GroupModel(obtainGroup(id));
    }
}
