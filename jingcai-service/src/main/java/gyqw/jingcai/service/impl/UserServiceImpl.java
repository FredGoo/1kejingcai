package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.UsersMapper;
import gyqw.jingcai.domain.User;
import gyqw.jingcai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UsersMapper usersMapper;

    @Autowired(required = false)
    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public int createUser(User user) {
        try {
            user.setdCreate(new Date());
            return this.usersMapper.insertSelective(user);
        } catch (Exception e) {
            logger.error("createUser error", e);
            return 0;
        }
    }

    @Override
    public int updateUser(User user) {
        try {
            return this.usersMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            logger.error("updateUser error", e);
            return 0;
        }
    }

    @Override
    public User findUserById(int id) {
        return this.usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByOpenId(String openId) {
        try {
            Condition condition = new Condition(User.class);
            condition.createCriteria().andEqualTo("cOpenId", openId);
            List<User> userList = this.usersMapper.selectByCondition(condition);
            if (userList != null && userList.size() > 0) {
                return userList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("findUserByOpenId error", e);
            return null;
        }
    }

    @Override
    public boolean deleteUserByid(int id) {
        // todo
        return false;
    }
}
