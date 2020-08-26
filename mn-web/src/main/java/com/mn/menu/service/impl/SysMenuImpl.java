package com.mn.menu.service.impl;

import com.mn.commonbean.exception.BusinessException;
import com.mn.menu.entity.param.SysMenuParam;
import com.mn.menu.entity.po.SysMenu;
import com.mn.menu.entity.vo.SysMenuTreeVo;
import com.mn.menu.entity.vo.SysMenuVo;
import com.mn.menu.mapper.SysMenuMapper;
import com.mn.menu.service.SysMenuService;
import com.mn.mnutil.SnowFlake;
import com.mn.module.authentication.AuthenConstants;
import com.mn.syspermission.entity.po.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service("sysMenuService")
public class SysMenuImpl implements SysMenuService {
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuTreeVo> listTree(SysMenuParam sysMenuParam) {
        if(AuthenConstants.SUPERADMIN.equals(sysMenuParam.getLoginName())){
            sysMenuParam.setLoginName(null);
        }
        //自我规定只能有三层菜单
        List<SysMenuTreeVo>  sysMenuTreeVoList = recursionQuearyTree(null,0L,3,1,sysMenuParam);
        return sysMenuTreeVoList;
    }

    @Override
    public SysMenuVo getSysMenuInfo(Long id) {
        return sysMenuMapper.getSysMenuInfo(id);
    }

    @Override
    public void save(SysMenu sysMenu) {
        if(sysMenu.getId() == null){
            sysMenu.setId(snowFlake.nextId());
            Date nowDate = new Date();
            sysMenu.setCreateDate(nowDate);
            sysMenu.setUpdateDate(nowDate);
            sysMenu.setIsDeleted(0);
            sysMenuMapper.insert(sysMenu);
        }else{
            sysMenuMapper.updateSysMenu(sysMenu);
        }
    }

    @Override
    public synchronized void deleteSysMeu(Long id) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",id);
        criteria.andEqualTo("isDeleted",0);
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(example);
        if(!sysMenuList.isEmpty()){
            throw new BusinessException("请先删除子菜单！");
        }
        sysMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 递归查询菜单
     * @param sysMenuTreeVo
     * @param parentId 父级ID，0为最顶级
     * @param limit 限制递归次数
     * @param currentIndex 第几次递归
     */
    private List<SysMenuTreeVo> recursionQuearyTree(SysMenuTreeVo sysMenuTreeVo,long parentId,int limit,int currentIndex,
                                                    SysMenuParam sysMenuParam) {
        List<SysMenuTreeVo> sysMenuTreeVoList = new ArrayList<>();
        //if(currentIndex<=limit){
            sysMenuParam.setParentId(parentId);
            List<SysMenu> sysMenuList = sysMenuMapper.selectMenuList(sysMenuParam);
            if(sysMenuList!=null && !sysMenuList.isEmpty()){
                for(SysMenu sysMenu : sysMenuList){
                    SysMenuTreeVo vo = new SysMenuTreeVo();
                    vo.setId(sysMenu.getId().toString());
                    vo.setText(sysMenu.getMenuName());
                    vo.setIconCls(sysMenu.getMenuIcon());
                    Map<String,Object> attrMap = new HashMap<>();
                    attrMap.put("menuUrl",sysMenu.getMenuUrl());
                    attrMap.put("isLeft",sysMenu.getIsLeft());
                    vo.setAttributes(attrMap);
                    sysMenuTreeVoList.add(vo);
                    //是子节点，并且含有权限，并且需要关联权限
                    if(sysMenu.getIsLeft().equals(1)){
                        if(sysMenuParam.getPermissionMap()!=null&& sysMenuParam.getPermissionMap().get(vo.getId())!=null){
                            List<SysPermission> permissionList = sysMenuParam.getPermissionMap().get(vo.getId());
                            List<SysMenuTreeVo> children = new ArrayList<>();
                            for(SysPermission permission:permissionList){
                                SysMenuTreeVo treeVo = new SysMenuTreeVo();
                                treeVo.setId(permission.getId().toString());
                                treeVo.setText(permission.getPermissionName());
                                treeVo.setIconCls(permission.getPermissionType().intValue()==0?"icon-standard-cog":"icon-standard-application-cascade");
                                Map<String,Object> treeAttrMap = new HashMap<>();
                                treeAttrMap.put("permissionUrl",permission.getPermissionUrl());
                                treeAttrMap.put("permissionType",permission.getPermissionType());
                                treeVo.setAttributes(treeAttrMap);
                                children.add(treeVo);
                            }
                            if(!children.isEmpty()){
                                vo.setChildren(children);
                            }
                        }

                    }else{
                        List<SysMenuTreeVo> children =  recursionQuearyTree( vo,Long.parseLong(vo.getId()),limit,currentIndex++,sysMenuParam);
                        if(children!=null && !children.isEmpty()){
                            vo.setChildren(children);
                        }
                    }

                }
            }
        //}
        //currentIndex = 1;
        return sysMenuTreeVoList;
    }
}
