package com.mn.menu.entity.po;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: 菜单Bean
 * @Author:Mloong
 * @Date :create in 2020/7/3-1:31
 * @Version 1.0
 * @Modified By:
 */
@Table(name="MN_SYS_MENU")
public class SysMenu {
    @Id
    private Long id;
    private Long parentId;
    private String menuName;
    private String menuUrl;
    private String menuIcon;
    private Integer menuLevel;
    private Integer menuOrder;
    private Integer menuEnable;
    private Integer isLeft;
    private Date createDate;
    private Date updateDate;
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
