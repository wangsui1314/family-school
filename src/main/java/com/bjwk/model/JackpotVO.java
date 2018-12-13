package com.bjwk.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: family-school
 * @description: {类描述}
 * @author: liqitian3344
 * @create: 2018-12-13 14:03
 */
@Data
public class JackpotVO {
    private Long  id;
    private String prizeName;
    private BigDecimal getProbabi;
    private Integer stockNum;
    private BigDecimal marketPrice;
    private Integer status;
    private String createTime;
    private String updateTime;
}
