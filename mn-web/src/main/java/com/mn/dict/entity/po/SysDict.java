package com.mn.dict.entity.po;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 字典主表类
 */
@Table(name = "sys_dict")
public class SysDict {

    @Column(name = "ID")
    @Id
    private String id;

    @Column(name="DICT_TYPE")
    private String dictType;
    @Column(name="DICT_DESC")
    private String dictDesc;
    @Column(name="CREATE_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
