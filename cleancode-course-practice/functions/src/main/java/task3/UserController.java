package task3;

import task3.thirdpartyjar.Controller;
import task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        User user = userAuthenticator.login(userName, password);
        if (user == null)
            generateFailLoginResponse();
        else
            generateSuccessLoginResponse(userName);
    }


}
