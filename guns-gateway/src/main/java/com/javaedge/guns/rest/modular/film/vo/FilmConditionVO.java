package com.javaedge.guns.rest.modular.film.vo;

import com.javaedge.guns.api.film.vo.CatVO;
import com.javaedge.guns.api.film.vo.SourceVO;
import com.javaedge.guns.api.film.vo.YearVO;
import lombok.Data;

import java.util.List;

/**
 * @author JavaEdge
 * @date 2019/9/14
 */
@Data
public class FilmConditionVO {

    private List<CatVO> catInfo;
    private List<SourceVO> sourceInfo;
    private List<YearVO> yearInfo;
}
