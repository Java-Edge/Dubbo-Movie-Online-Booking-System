package com.javaedge.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.javaedge.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.javaedge.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.javaedge.guns.api.user.UserAPI;
import com.javaedge.guns.rest.modular.auth.util.JwtTokenUtil;
import com.javaedge.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.javaedge.guns.rest.modular.vo.ResponseVO.success;

/**
 * 请求验证
 *
 * @author JavaEdge
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Reference(interfaceClass = UserAPI.class, check = false)
    private UserAPI userAPI;

    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVO createAuthenticationToken(AuthRequest authRequest) {

        boolean validate = true;
        // 去掉guns自身携带的用户名密码验证机制，而使用自定义的
        int userId = userAPI.login(authRequest.getUserName(), authRequest.getPassword());
        if (userId == 0) {
            validate = false;
        }

        if (validate) {
            // randomKey和token已经生成完毕
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken("" + userId, randomKey);
            // 返回值
            return ResponseVO.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVO.serviceFail("用户名或密码错误");
        }
    }
}
