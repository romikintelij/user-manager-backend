package zab.romik.usermanagement.usermanagement.groups;

import zab.romik.usermanagement.usermanagement.groups.domain.Group;
import zab.romik.usermanagement.usermanagement.groups.model.GroupModel;
import zab.romik.usermanagement.usermanagement.users.model.UserModel;

import java.util.List;

public interface GroupService {
    GroupModel create(GroupModel model);

    void delete(long id);

    GroupModel update(long id, GroupModel model);

    GroupModel findById(long id);

    List<GroupModel> findAllGroups();

    List<UserModel> findUsersInGroup(long groupId);
}
