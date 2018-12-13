package com.bjwk.model.req;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: family-school
 * @description: {类描述}
 * @author: liqitian3344
 * @create: 2018-12-13 13:14
 */
@Data
public class JackpotReq {
    @NonNull
    private String prizeName;
    @NonNull
    private BigDecimal getProba;
    @NonNull
    private Integer stockNum;
    private BigDecimal marketPrice;
}
