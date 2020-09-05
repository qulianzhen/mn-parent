package com.mn.sysuser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import com.mn.sysuser.entity.param.SysUserParam;
import com.mn.sysuser.entity.po.SysUser;
import com.mn.sysuser.mapper.SysUserMapper;
import com.mn.sysuser.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName:     SysUserServiceImpl.java
 * @Description:   用户表的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-07 00:20:30
 */

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{
 
	@Autowired
	private SysUserMapper sysUserMapper;
	 
	@Override
	public PageInfo<SysUser> listPage(PageQuerier<SysUserParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
		List<SysUser> sysUserList = sysUserMapper.listSysUser(pageQuerierParam.getSearch());
        return new PageInfo<SysUser>(sysUserList);
	}

	@Override
    public List<SysUser> list(SysUserParam param) {
    	return sysUserMapper.listSysUser(param);
    }

	@Override
    @Transactional
    public void insert(SysUserParam param) {
		SysUser sysUser = PojoConvertUtil.convertPojo(param,SysUser.class);
		SimpleHash hash = new SimpleHash("md5", sysUser.getPassword(), sysUser.getUserName(), 2);
		sysUser.setPassword(hash.toHex());
		sysUser.setIsDeleted(0);
		sysUser.setIslock(0);
		sysUser.setCreateDate(new Date());
		sysUserMapper.insertSysUser(sysUser);
    }

	@Override
    @Transactional
    public void update(SysUserParam param) {
		sysUserMapper.updateSysUser(PojoConvertUtil.convertPojo(param,SysUser.class));
    }

		
	@Override
	public SysUser get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return sysUserMapper.getSysUser(id);
	}

	@Override
	public SysUser get(String loginName) {
		if (StringUtils.isEmpty(loginName)) {
			return null;
		}
		return sysUserMapper.getSysUserByLoginName(loginName);
	}


	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		sysUserMapper.deleteSysUser(ids);
	}

	@Override
	public SysUser findSysUserByUserName(String userName) {
		Example example = new Example(SysUser.class);
		//example.setTableName("MN_SYS_USER");//写上了没用，最后是在Bean上加注解解决的指定表名问题
		example.selectProperties("id", "password","islock","nickName");
		example.createCriteria().andEqualTo("userName",userName)
				.andEqualTo("isDeleted",0);
		return sysUserMapper.selectOneByExample(example);
	}

	@Override
	@Cacheable(cacheNames="MN_USER_PERMISSION", key="#id.toString()")
	public Set<String> getUrlPermitByUserId(Long id) {
		List<String> l = sysUserMapper.getUrlPermitByUserId(id);
		Set<String> urlPermitSet = new HashSet<String>();
		String permitUrl = "";
		for(int i=0,len=l.size();i<len;i++){
			permitUrl = l.get(i);
			if(permitUrl != null && !permitUrl.trim().equals("")){
				urlPermitSet.add(permitUrl);
			}
		}
		return urlPermitSet;
	}
}