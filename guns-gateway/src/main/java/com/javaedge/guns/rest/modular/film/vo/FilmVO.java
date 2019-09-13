package com.javaedge.guns.rest.modular.film.vo;

import lombok.Data;

import java.util.List;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class FilmVO {

    private Integer filmNum;
    private List<FilmInfo> filmInfos;

}
