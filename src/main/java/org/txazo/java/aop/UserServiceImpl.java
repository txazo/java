package org.txazo.java.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void addUser(String userName) {
        LOGGER.info("add user {}", userName);
    }

}
