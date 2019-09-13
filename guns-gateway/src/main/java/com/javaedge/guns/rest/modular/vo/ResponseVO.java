package com.javaedge.guns.rest.modular.vo;

import lombok.Data;

/**
 * 前端交互响应对象
 *
 * @author JavaEdge
 */
@Data
public class ResponseVO<M> {

    /**
     * 返回状态
     * 0    : 成功
     * 1    : 业务失败
     * 999  : 系统异常】
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据实体
     */
    private M data;


    private ResponseVO() {
    }


    public static <M> ResponseVO success(M m) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);

        return responseVO;
    }

    public static <M> ResponseVO success(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }

    /**
     * 业务异常
     * @param msg
     * @param <M>
     * @return
     */
    public static <M> ResponseVO serviceFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);

        return responseVO;
    }

    /**
     * 系统异常,try/catch
     * @param msg
     * @param <M>
     * @return
     */
    public static <M> ResponseVO appFail(String msg) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);

        return responseVO;
    }

}
