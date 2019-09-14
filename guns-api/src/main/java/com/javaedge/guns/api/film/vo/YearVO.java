package com.javaedge.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class YearVO implements Serializable {

    private String yearId;
    private String yearName;
    private Boolean isActive;

    public void setActive(boolean status) {
        this.isActive = status;
    }
}
