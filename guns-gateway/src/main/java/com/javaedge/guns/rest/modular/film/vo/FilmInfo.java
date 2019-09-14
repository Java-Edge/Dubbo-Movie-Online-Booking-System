package com.javaedge.guns.rest.modular.film.vo;

import lombok.Data;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class FilmInfo {
    private String fileId;
    private int fileType;
    private String imgAddress;
    private String fileName;
    private String fileScore;
    private int expectNum;
    private String showTime;
    private int boxNum;
    private String score;
}
