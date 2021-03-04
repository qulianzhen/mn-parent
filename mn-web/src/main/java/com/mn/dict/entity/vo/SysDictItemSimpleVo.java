package com.mn.dict.entity.vo;

import java.io.Serializable;

/**
 * 字典主表类
 */
public class SysDictItemSimpleVo implements Serializable {

    private String itemName;
    private String itemValue;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

}
