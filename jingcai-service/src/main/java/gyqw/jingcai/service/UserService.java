package gyqw.jingcai.service;

import gyqw.jingcai.domain.User;

/**
 * 用户接口
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return 创建成功的用户信息
     */
    int createUser(User user);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findUserById(int id);

    /**
     * 根据openId获取用户信息
     *
     * @param openId
     * @return
     */
    User findUserByOpenId(String openId);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true/false
     */
    boolean deleteUserByid(int id);

}
