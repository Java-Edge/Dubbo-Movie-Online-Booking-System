package com.javaedge.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.javaedge.guns.api.user.UserAPI;
import com.javaedge.guns.api.user.vo.UserInfoModel;
import com.javaedge.guns.api.user.vo.UserModel;
import com.javaedge.guns.rest.common.CurrentUser;
import com.javaedge.guns.rest.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author JavaEdge
 */
@RequestMapping("/user/")
@RestController
@Slf4j
public class UserController {

    @Reference(interfaceClass = UserAPI.class, check = false)
    private UserAPI userAPI;

    @PostMapping(value = "register")
    public ResponseVO register(UserModel userModel) {
        if (userModel.getUsername() == null || userModel.getUsername().trim().length() == 0) {
            return ResponseVO.serviceFail("用户名不能为空");
        }
        if (userModel.getPassword() == null || userModel.getPassword().trim().length() == 0) {
            return ResponseVO.serviceFail("密码不能为空");
        }

        boolean isSuccess = userAPI.register(userModel);
        if (isSuccess) {
            log.info("用户 username : {} 注册成功", userModel.getUsername());
            return ResponseVO.success("注册成功");
        } else {
            return ResponseVO.serviceFail("注册失败");
        }
    }

    @PostMapping(value = "check")
    public ResponseVO check(String username) {
        if (username != null && username.trim().length() > 0) {
            boolean notExists = userAPI.checkUsername(username);
            if (notExists) {
                return ResponseVO.success("用户名不存在");
            } else {
                return ResponseVO.serviceFail("用户名已存在");
            }

        } else {
            return ResponseVO.serviceFail("用户名不能为空");
        }
    }

    @GetMapping(value = "logout")
    public ResponseVO logout() {
        /**
         * 应用：
         *  1、前端存储JWT 【七天】 ： JWT的刷新
         *  2、服务器端会存储活动用户信息【30分钟】
         *  3、JWT里的userId为key，查找活跃用户
         *
         * 退出：
         *  1、前端删除JWT
         *  2、后端服务器删除活跃用户缓存
         *
         * 现状：
         *  1、前端删除JWT
         *
         *  非该项目重点,不赘述!
         */
        return ResponseVO.success("用户退出成功");
    }

    @GetMapping(value = "getUserInfo")
    public ResponseVO getUserInfo() {
        // 获取当前登陆用户
        String userId = CurrentUser.getCurrentUser();
        if (userId != null && userId.trim().length() > 0) {
            // 将用户ID传入后端进行查询
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfo = userAPI.getUserInfo(uuid);
            if (userInfo != null) {
                return ResponseVO.success(userInfo);
            } else {
                return ResponseVO.appFail("用户信息查询失败");
            }
        } else {
            return ResponseVO.serviceFail("用户未登陆");
        }
    }

    @PostMapping(value = "updateUserInfo")
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel) {
        // 获取当前登陆用户
        String userId = CurrentUser.getCurrentUser();
        if (userId != null && userId.trim().length() > 0) {
            // 将用户ID传入后端进行查询
            int uuid = Integer.parseInt(userId);
            // 判断当前登陆人员的ID与修改的结果ID是否一致
            if (uuid != userInfoModel.getUuid()) {
                return ResponseVO.serviceFail("请修改您个人的信息");
            }

            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if (userInfo != null) {
                return ResponseVO.success(userInfo);
            } else {
                return ResponseVO.appFail("用户信息修改失败");
            }
        } else {
            return ResponseVO.serviceFail("用户未登陆");
        }
    }

}
