package com.mn.menu.service;

import com.mn.menu.entity.param.SysMenuParam;
import com.mn.menu.entity.po.SysMenu;
import com.mn.menu.entity.vo.SysMenuTreeVo;
import com.mn.menu.entity.vo.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    List<SysMenuTreeVo> listTree(SysMenuParam sysMenuParam);

    SysMenuVo getSysMenuInfo(Long id);

    void save(SysMenu sysMenu);

    void deleteSysMeu(Long id);
}
