package com.bjwk.model.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: family-school
 * @description: {类描述}
 * @author: liqitian3344
 * @create: 2018-12-13 13:16
 */
@Data
public class JackpotPO {
    private Long  id;
    private String prizeName;
    private BigDecimal getProbabi;
    private Integer stockNum;
    private BigDecimal marketPrice;
    private String prizeImg;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
