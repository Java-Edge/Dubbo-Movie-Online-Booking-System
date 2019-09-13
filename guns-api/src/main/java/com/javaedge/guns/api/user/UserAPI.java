package com.javaedge.guns.api.user;

import com.javaedge.guns.api.user.vo.UserInfoModel;
import com.javaedge.guns.api.user.vo.UserModel;

/**
 * @author JavaEdge
 */
public interface UserAPI {

    /**
     * 登录
     * @param username
     * @param password 注意这里是加密前的密码!!!
     * @return 在后台验证登录成功后,将用户id从后台传至前端
     *         通过JWT传至客户端,客户端将一直携带用户id,这就可以将用户id缓存至Redis与客户端的JWT中的用户id对比
     */
    int login(String username, String password);

    /**
     * 注册
     * @param userModel
     * @return
     */
    boolean register(UserModel userModel);

    /**
     * 验证用户名
     * @param username
     * @return true:用户名可用
     */
    boolean checkUsername(String username);

    /**
     * 获取用户信息
     * @param uuid
     * @return
     */
    UserInfoModel getUserInfo(int uuid);

    /**
     * 更新信息
     * @param userInfoModel
     * @return
     */
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}

