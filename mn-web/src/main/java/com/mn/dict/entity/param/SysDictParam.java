package com.mn.dict.entity.param;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: (描述)
 * @Author:Mloong
 * @Date :create in 2020/6/26-1:06
 * @Version 1.0
 * @Modified By:
 */
public class SysDictParam implements Serializable {

    private Long id;
    @NotBlank
    @Length(min=1, max=8)
    private String dictType;
    @NotBlank
    private String dictDesc;
    /**
     * 是否需要排除id(更新字典类型标志时验重用)
     */
    private Integer notThisIdFlag;

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



    public Integer getNotThisIdFlag() {
        return notThisIdFlag;
    }

    public void setNotThisIdFlag(Integer notThisIdFlag) {
        this.notThisIdFlag = notThisIdFlag;
    }
}
