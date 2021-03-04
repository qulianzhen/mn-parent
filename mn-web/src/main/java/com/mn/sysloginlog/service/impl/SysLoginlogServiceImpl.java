package com.mn.sysloginlog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.mnutil.SnowFlake;
import com.mn.module.page.PageQuerier;
import com.mn.sysloginlog.entity.param.SysLoginlogParam;
import com.mn.sysloginlog.entity.po.SysLoginlog;
import com.mn.sysloginlog.mapper.SysLoginlogMapper;
import com.mn.sysloginlog.service.SysLoginlogService;
import com.mn.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName:     SysLoginlogServiceImpl.java
 * @Description:   登录日志的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-07 17:32:43
 */

@Service("sysLoginlogService")
public class SysLoginlogServiceImpl implements SysLoginlogService{
 
	@Autowired
	private SysLoginlogMapper sysLoginlogMapper;
	@Autowired
	private SnowFlake snowFlake;
	@Autowired
	private HttpServletRequest request;
	 
	@Override
	public PageInfo<SysLoginlog> listPage(PageQuerier<SysLoginlogParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<SysLoginlog>(sysLoginlogMapper.listSysLoginlog(pageQuerierParam.getSearch()));
	}

	@Override
    @Transactional
    public void insert(SysLoginlogParam param) {
		SysLoginlog sysLoginlog = PojoConvertUtil.convertPojo(param,SysLoginlog.class);
		sysLoginlog.setId(snowFlake.nextId());
		sysLoginlog.setLoginlogDate(new Date());
		sysLoginlog.setLoginlogIp(IPUtil.getRequestRealIp(request));
		sysLoginlogMapper.insertSysLoginlog(sysLoginlog);
    }
}