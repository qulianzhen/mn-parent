package com.mn.dict.entity.vo;

import java.io.Serializable;

/**
 * 字典主表类
 */
public class SysDictVo implements Serializable {

    private Long id;

    private String dictType;
    private String dictDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

}
