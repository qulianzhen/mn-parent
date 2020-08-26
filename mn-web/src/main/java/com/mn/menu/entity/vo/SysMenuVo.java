package com.mn.menu.entity.vo;

/**
 * @Description: 菜单VO
 * @Author:Mloong
 * @Date :create in 2020/7/3-1:36
 * @Version 1.0
 * @Modified By:
 */
public class SysMenuVo{
    private String id;
    private Long parentId;
    private String parentName;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Integer menuLevel;
    private Integer menuOrder;
    private Integer menuEnable;
    private Integer isLeft;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Integer getMenuEnable() {
        return menuEnable;
    }

    public void setMenuEnable(Integer menuEnable) {
        this.menuEnable = menuEnable;
    }

    public Integer getIsLeft() {
        return isLeft;
    }

    public void setIsLeft(Integer isLeft) {
        this.isLeft = isLeft;
    }
}
