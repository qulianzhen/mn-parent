package com.mn.menu.entity.vo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 菜单树VO
 * @Author:Mloong
 * @Date :create in 2020/7/3-1:36
 * @Version 1.0
 * @Modified By:
 */
public class SysMenuTreeVo {

    private String id;
    private String text;
    private String iconCls;
    private Boolean checked;
    private String state;//open  closed
    private Map<String,Object> attributes;
    private List<SysMenuTreeVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<SysMenuTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuTreeVo> children) {
        this.children = children;
    }
}
