package nisum.com.user_management.domain.port.create;

import nisum.com.user_management.domain.User;

public interface UserCreatorRepository {

    User save(User user);

}
