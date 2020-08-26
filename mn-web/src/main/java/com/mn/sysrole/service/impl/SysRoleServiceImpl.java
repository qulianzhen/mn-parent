package com.mn.sysrole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.sysrole.entity.param.SysRoleParam;
import com.mn.sysrole.entity.param.SysUserRoleParam;
import com.mn.sysrole.entity.po.SysRole;
import com.mn.sysrole.entity.po.SysUserRole;
import com.mn.sysrole.mapper.SysRoleMapper;
import com.mn.sysrole.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName:     SysRoleServiceImpl.java
 * @Description:   角色表的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-04 21:44:06
 */

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{
 
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SnowFlake snowFlake;
	 
	@Override
	public PageInfo<SysRole> listPage(PageQuerier<SysRoleParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<SysRole>(sysRoleMapper.listSysRole(pageQuerierParam.getSearch()));
	}

	@Override
    public List<SysRole> list(SysRoleParam param) {
    	return sysRoleMapper.listSysRole(param);
    }

	@Override
    @Transactional
    public void insert(SysRoleParam param) {
		SysRole sysRole = PojoConvertUtil.convertPojo(param,SysRole.class);
		sysRole.setRoleEnable(0);
		sysRole.setIsDeleted(0);
		sysRole.setCreateDate(new Date());
		sysRoleMapper.insertSysRole(sysRole);
    }

	@Override
    @Transactional
    public void update(SysRoleParam param) {
		sysRoleMapper.updateSysRole(PojoConvertUtil.convertPojo(param,SysRole.class));
    }

		
	@Override
	public SysRole get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return sysRoleMapper.getSysRole(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		sysRoleMapper.deleteSysRole(ids);
	}

	@Override
	public void saveUserRole(List<SysUserRoleParam> paramList) {
		if(paramList!=null && !paramList.isEmpty()){
			SysUserRoleParam sysUserRoleParamFirst = paramList.get(0);
			Long userId = sysUserRoleParamFirst.getUserId();
			Integer ClearAllRoleFlag = sysUserRoleParamFirst.getClearAllRoleFlag();
			sysRoleMapper.deleteUserRole(userId);
			if(paramList.size() == 1 && ClearAllRoleFlag !=null && ClearAllRoleFlag == 1){
				return;
			}
			paramList.stream().forEach(m->m.setId(snowFlake.nextId()));
			sysRoleMapper.saveUserRole(PojoConvertUtil.convertPojos(paramList,SysUserRole.class));
		}
	}

	@Override
	public List<Long> getSysRoleIdByUserId(Long userId) {
		List<Long> roleList = sysRoleMapper.listRoleByUserId(userId);
		return roleList;
	}
}