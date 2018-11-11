package com.bjwk.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: family-school
 * @description: 课程列表vo
 * @author: liqitian.
 * @create: 2018-09-09 17:45
 **/
@Data
public class CourseVideoBankVO {

    private int courseVideoBankId;

    private String coverMapImage;

    private String title;

    private String lecturer;

    private String contentValidity;

    private Integer starLevel;

    private Integer videoTime;

    private Integer isCharge;

    private BigDecimal yuan;

    private String category;

    private Date updateTime;

    private Boolean isCollection;
}

