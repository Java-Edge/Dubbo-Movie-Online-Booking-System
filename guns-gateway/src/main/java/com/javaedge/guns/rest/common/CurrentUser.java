package com.javaedge.guns.rest.common;

/**
 * 获取当前对象
 *
 * @author JavaEdge
 */
public class CurrentUser {

    /**
     * 线程绑定的存储空间
     */
    private static final ThreadLocal<String> THREAD_LOCAL_USER = new ThreadLocal<>();

    public static void saveUserId(String userId) {
        THREAD_LOCAL_USER.set(userId);
    }

    public static String getCurrentUser() {
        return THREAD_LOCAL_USER.get();
    }
    // 将用户信息放入存储空间
//    public static void saveUserInfo(UserInfoModel userInfoModel){
//        threadLocal.set(userInfoModel);
//    }
//
//    // 将用户信息取出
//    public static UserInfoModel getCurrentUser(){
//        return threadLocal.get();
//    }

}
