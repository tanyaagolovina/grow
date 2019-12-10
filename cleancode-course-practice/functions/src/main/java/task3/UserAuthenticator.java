package task3;

import task3.thirdpartyjar.SessionManager;
import task3.thirdpartyjar.User;
import task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public User login(String userName, String password) {
        return loginUser(getUserByName(userName), password);
    }

    private User loginUser(User user, String password) {
        if (isPasswordCorrect(user, password)) {
            sessionManager.setCurrentUser(user);
            return user;
        }
        return null;
    }


}
