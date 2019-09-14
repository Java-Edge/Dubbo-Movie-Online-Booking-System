package com.javaedge.guns.rest.modular.film.vo;

import com.javaedge.guns.api.film.vo.BannerVO;
import com.javaedge.guns.api.film.vo.FilmInfo;
import com.javaedge.guns.api.film.vo.FilmVO;
import lombok.Data;

import java.util.List;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class FilmIndexVO {

    private List<BannerVO> banners;
    private FilmVO hotFilms;
    private FilmVO soonFilms;
    private List<FilmInfo> boxRanking;
    private List<FilmInfo> expectRanking;
    private List<FilmInfo> top100;
}
