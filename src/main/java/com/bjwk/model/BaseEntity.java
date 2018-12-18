package com.bjwk.model;

import com.bjwk.model.listener.BaseEntityListener;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 16:57 2018/6/29
 **/

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity implements Serializable {

    /**
     * 首次插入时间
     */
    protected Date createTime;

    /**
     * 最后修改时间
     */
    protected Date updateTime;


}