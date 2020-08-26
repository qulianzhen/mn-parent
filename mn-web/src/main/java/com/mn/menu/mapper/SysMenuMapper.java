package com.mn.menu.mapper;

import com.mn.commonbean.tkmapper.MyMapper;
import com.mn.menu.entity.param.SysMenuParam;
import com.mn.menu.entity.po.SysMenu;
import com.mn.menu.entity.vo.SysMenuVo;

import java.util.List;

public interface SysMenuMapper extends MyMapper<SysMenu> {

    SysMenuVo getSysMenuInfo(Long id);

    void updateSysMenu(SysMenu sysMenu);

    List<SysMenu> selectMenuList(SysMenuParam sysMenuParam);
}
