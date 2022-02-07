package example.application.repository;

import example.domain.model.user.User;
import example.domain.model.user.UserName;

public interface UserRepository {
    User getUserByName(UserName username);
    void registerUser(User user);
}
