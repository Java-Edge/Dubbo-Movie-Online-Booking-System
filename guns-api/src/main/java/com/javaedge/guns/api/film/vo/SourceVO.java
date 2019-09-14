package com.javaedge.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class SourceVO implements Serializable {

    private String sourceId;
    private String sourceName;
    private Boolean isActive;

    public void setActive(boolean status) {
        this.isActive = status;
    }
}
