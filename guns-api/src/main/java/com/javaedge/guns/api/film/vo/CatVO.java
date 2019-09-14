package com.javaedge.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class CatVO implements Serializable {

    private String catId;
    private String catName;
    private Boolean isActive;

    public void setActive(boolean status) {
        this.isActive = status;
    }
}
