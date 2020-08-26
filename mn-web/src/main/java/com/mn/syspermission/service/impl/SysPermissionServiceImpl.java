package com.mn.syspermission.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.menu.entity.param.SysMenuParam;
import com.mn.menu.entity.vo.SysMenuTreeVo;
import com.mn.menu.service.SysMenuService;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.syspermission.entity.param.SysPermissionParam;
import com.mn.syspermission.entity.param.SysRolePermissionParam;
import com.mn.syspermission.entity.po.SysPermission;
import com.mn.syspermission.entity.po.SysRolePermission;
import com.mn.syspermission.entity.vo.SysPermissionVo;
import com.mn.syspermission.mapper.SysPermissionMapper;
import com.mn.syspermission.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName:     SysPermissionServiceImpl.java
 * @Description:   权限表的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-15 12:35:14
 */

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService{
 
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SnowFlake snowFlake;
	 
	@Override
	public PageInfo<SysPermission> listPage(PageQuerier<SysPermissionParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<SysPermission>(sysPermissionMapper.listSysPermission(pageQuerierParam.getSearch()));
	}

	@Override
    public List<SysPermission> list(SysPermissionParam param) {
    	return sysPermissionMapper.listSysPermission(param);
    }

	@Override
    @Transactional
    public void insert(SysPermissionParam param) {
		SysPermission sysPermission = PojoConvertUtil.convertPojo(param,SysPermission.class);
		sysPermission.setIsDeleted(0);
		Date nowDate = new Date();
		sysPermission.setCreateDate(nowDate);
		sysPermission.setUpdateDate(nowDate);
		sysPermissionMapper.insertSysPermission(sysPermission);
    }

	@Override
    @Transactional
    public void update(SysPermissionParam param) {
		param.setUpdateDate(new Date());
		sysPermissionMapper.updateSysPermission(PojoConvertUtil.convertPojo(param,SysPermission.class));
    }

		
	@Override
	public SysPermission get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return sysPermissionMapper.getSysPermission(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		sysPermissionMapper.deleteSysPermission(ids);
	}

	@Override
	public List<SysMenuTreeVo> listTree(SysPermissionParam sysPermissionParam) {
		List<SysPermission> permissionList = this.list(new SysPermissionParam());
		Map<String,List<SysPermission>> menuPermissionMap = permissionList.stream().collect(Collectors.groupingBy(m->m.getPermissionParentId().toString()));
		SysMenuParam menuParam = new SysMenuParam();
		menuParam.setPermissionMap(menuPermissionMap);
		List<SysMenuTreeVo> menuTreeList =  sysMenuService.listTree(menuParam);
		return menuTreeList;
	}

	@Override
	public SysPermissionVo getSysPermissionInfo(Long id) {
		return sysPermissionMapper.getSysPermissionInfo(id);
	}

	@Override
	public void saveRolePermission(List<SysRolePermissionParam> paramList) {
		if(paramList!=null && !paramList.isEmpty()){
			SysRolePermissionParam sysRolePermissionParamFirst = paramList.get(0);
			Long roleId = sysRolePermissionParamFirst.getRoleId();
			Integer ClearAllRoleFlag = sysRolePermissionParamFirst.getClearAllPermissionFlag();
			sysPermissionMapper.deleteRolePermission(roleId);
			if(paramList.size() == 1 && ClearAllRoleFlag !=null && ClearAllRoleFlag == 1){
				return;
			}
			paramList.stream().forEach(m->m.setId(snowFlake.nextId()));
			sysPermissionMapper.saveRolePermission(PojoConvertUtil.convertPojos(paramList,SysRolePermission.class));
		}
	}

	@Override
	public List<Long> getSysPermissionIdByRoleId(Long roleId) {
		List<Long> permissionList = sysPermissionMapper.listPermissionByRoleId(roleId);
		return permissionList;
	}
}