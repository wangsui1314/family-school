package com.bjwk.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: family-school
 * @description: 视屏课程 下方目录
 * @author: liqitian.
 * @create: 2018-09-10 00:40
 **/
@Data
public class CourseVideoBankCatalogDTO {
    private int courseVideoBankId;

    private String title;

    private Integer videoTime;

    private String packageNum;
}
