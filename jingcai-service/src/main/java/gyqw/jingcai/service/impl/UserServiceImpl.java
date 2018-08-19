package gyqw.jingcai.service.impl;

import gyqw.jingcai.domain.User;
import gyqw.jingcai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User user) {
        // todo
        return null;
    }

    @Override
    public User findUserById(int id) {
        // todo
        return null;
    }

    @Override
    public boolean deleteUserByid(int id) {
        // todo
        return false;
    }
}
