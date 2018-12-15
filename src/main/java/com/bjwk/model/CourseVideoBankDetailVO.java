package com.bjwk.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: family-school
 * @description: 视屏课程详情
 * @author: liqitian.
 * @create: 2018-09-10 00:06
 **/
@Data
public class CourseVideoBankDetailVO {

    private int courseVideoBankId;

    private String video;
    private String videoLocalPath;
    private String title;
    private String lecturerHeaderImg;
    private String lecturer;

    private String contentValidity;

    private Integer starLevel;

    private Integer videoTime;

    private Integer isCharge;

    private BigDecimal yuan;

    private Integer category;
    private Boolean isCollection;

    private Long browseNumber;

    private String packageNum;
}
