package org.txazo.java.aop;

public class UserServiceImpl implements UserService {

    @Override
    public String getUserName(int userId) {
        return "user-" + userId;
    }

}
