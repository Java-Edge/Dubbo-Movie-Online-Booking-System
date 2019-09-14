package com.javaedge.guns.api.film;

import com.javaedge.guns.api.film.vo.*;

import java.util.List;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
public interface FilmServiceApi {

    /**
     * 获取banners
     *
     * @return
     */
    List<BannerVO> getBanners();

    /**
     * 获取热映影片
     *
     * @param isLimit
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    /**
     * 获取即将上映影片[受欢迎程度做排序]
     *
     * @param isLimit
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    FilmVO getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);

    /**
     * 获取经典影片
     *
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId);
    /*
        在正式项目中，推荐大家使用的做法
     */
//    // 获取热映影片
//    FilmVO getHotFilms(int nowPage,int nums ...);

    /**
     * 获取票房排行榜
     *
     * @return
     */
    List<FilmInfo> getBoxRanking();

    /**
     * 获取人气排行榜
     *
     * @return
     */
    List<FilmInfo> getExpectRanking();

    /**
     * 获取Top100
     *
     * @return
     */
    List<FilmInfo> getTop();

    // ==== 获取影片条件接口

    /**
     * 分类条件
     *
     * @return
     */
    List<CatVO> getCats();

    /**
     * 片源条件
     *
     * @return
     */
    List<SourceVO> getSources();

    /**
     * 获取年代条件
     *
     * @return
     */
    List<YearVO> getYears();

    /**
     * 根据影片ID或者名称获取影片信息
     *
     * @param searchType
     * @param searchParam
     * @return
     */
    FilmDetailVO getFilmDetail(int searchType, String searchParam);

    /**
     * 获取影片描述信息
     *
     * @param filmId
     * @return
     */
    FilmDescVO getFilmDesc(String filmId);

    /**
     * 获取图片信息
     *
     * @param filmId
     * @return
     */
    ImgVO getImgs(String filmId);

    /**
     * 获取导演信息
     *
     * @param filmId
     * @return
     */
    ActorVO getDectInfo(String filmId);

    /**
     * 获取演员信息
     *
     * @param filmId
     * @return
     */
    List<ActorVO> getActors(String filmId);

}
