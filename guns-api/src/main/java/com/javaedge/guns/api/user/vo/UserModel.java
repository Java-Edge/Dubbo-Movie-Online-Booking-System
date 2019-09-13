package com.javaedge.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 仅注册时使用
 * @author JavaEdge
 */
@Data
public class UserModel implements Serializable {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

}
