package com.javaedge.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.javaedge.guns.api.user.UserAPI;
import com.javaedge.guns.api.user.vo.UserInfoModel;
import com.javaedge.guns.api.user.vo.UserModel;
import com.javaedge.guns.core.util.MD5Util;
import com.javaedge.guns.rest.common.persistence.dao.JavaEdgeUserTMapper;
import com.javaedge.guns.rest.common.persistence.model.JavaEdgeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JavaEdge
 */
@Component
@Service(interfaceClass = UserAPI.class, loadbalance = "roundrobin")
public class UserServiceImpl implements UserAPI {

    @Autowired
    private JavaEdgeUserTMapper javaEdgeUserTMapper;

    @Override
    public boolean register(UserModel userModel) {
        // 将注册信息实体转换为数据实体[javaedge_user_t]
        JavaEdgeUser javaEdgeUser = new JavaEdgeUser();
        javaEdgeUser.setUserName(userModel.getUsername());
        javaEdgeUser.setEmail(userModel.getEmail());
        javaEdgeUser.setAddress(userModel.getAddress());
        javaEdgeUser.setUserPhone(userModel.getPhone());
        // 创建时间和修改时间 -> current_timestamp

        // 数据加密更好的选择 【MD5混淆加密 + 盐值 -> Shiro加密】这里非重点,不赘述
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        javaEdgeUser.setUserPwd(md5Password);

        // 将数据实体存入数据库
        Integer insert = javaEdgeUserTMapper.insert(javaEdgeUser);
        return insert > 0;
    }

    @Override
    public int login(String username, String password) {
        // 根据登陆账号从DB获取信息
        JavaEdgeUser javaEdgeUser = new JavaEdgeUser();
        javaEdgeUser.setUserName(username);

        JavaEdgeUser result = javaEdgeUserTMapper.selectOne(javaEdgeUser);

        // 获取到的结果，然后与加密后的密码做匹配
        if (result != null && result.getUuid() > 0) {
            String md5Password = MD5Util.encrypt(password);
            if (result.getUserPwd().equals(md5Password)) {
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<JavaEdgeUser> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name", username);
        Integer result = javaEdgeUserTMapper.selectCount(entityWrapper);
        if (result != null && result > 0) {
            return false;
        } else {
            return true;
        }
    }

    private UserInfoModel do2UserInfo(JavaEdgeUser javaEdgeUser) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUuid(javaEdgeUser.getUuid());
        userInfoModel.setHeadAddress(javaEdgeUser.getHeadUrl());
        userInfoModel.setPhone(javaEdgeUser.getUserPhone());
        userInfoModel.setUpdateTime(javaEdgeUser.getUpdateTime().getTime());
        userInfoModel.setEmail(javaEdgeUser.getEmail());
        userInfoModel.setUsername(javaEdgeUser.getUserName());
        userInfoModel.setNickname(javaEdgeUser.getNickName());
        userInfoModel.setLifeState("" + javaEdgeUser.getLifeState());
        userInfoModel.setBirthday(javaEdgeUser.getBirthday());
        userInfoModel.setAddress(javaEdgeUser.getAddress());
        userInfoModel.setSex(javaEdgeUser.getUserSex());
        userInfoModel.setBeginTime(javaEdgeUser.getBeginTime().getTime());
        userInfoModel.setBiography(javaEdgeUser.getBiography());

        return userInfoModel;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        // 根据主键查询用户信息 [JavaEdgeUser]
        JavaEdgeUser javaEdgeUser = javaEdgeUserTMapper.selectById(uuid);
        // 将JavaEdgeUser转换UserInfoModel
        UserInfoModel userInfoModel = do2UserInfo(javaEdgeUser);
        // 返回UserInfoModel
        return userInfoModel;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        // 将传入的参数转换为DO 【JavaEdgeUser】
        JavaEdgeUser javaEdgeUser = new JavaEdgeUser();
        javaEdgeUser.setUuid(userInfoModel.getUuid());
        javaEdgeUser.setNickName(userInfoModel.getNickname());
        javaEdgeUser.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        javaEdgeUser.setBirthday(userInfoModel.getBirthday());
        javaEdgeUser.setBiography(userInfoModel.getBiography());
        javaEdgeUser.setBeginTime(null);
        javaEdgeUser.setHeadUrl(userInfoModel.getHeadAddress());
        javaEdgeUser.setEmail(userInfoModel.getEmail());
        javaEdgeUser.setAddress(userInfoModel.getAddress());
        javaEdgeUser.setUserPhone(userInfoModel.getPhone());
        javaEdgeUser.setUserSex(userInfoModel.getSex());
        javaEdgeUser.setUpdateTime(null);

        // DO存入数据库
        Integer integer = javaEdgeUserTMapper.updateById(javaEdgeUser);
        if (integer > 0) {
            // 将数据从数据库中读取出来
            UserInfoModel userInfo = getUserInfo(javaEdgeUser.getUuid());
            // 将结果返回给前端
            return userInfo;
        } else {
            return null;
        }
    }
}
