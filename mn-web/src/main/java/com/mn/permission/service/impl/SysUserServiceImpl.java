package com.mn.permission.service.impl;

import com.mn.permission.entity.po.SysUser;
import com.mn.permission.mapper.SysUserMapper;
import com.mn.permission.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 登录用户服务
 * @Author:Mloong
 * @Date :create in 2019/3/1-1:26
 * @Version 1.0
 * @Modified By:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserByUserName(String userName) {
        Example example = new Example(SysUser.class);
        example.selectProperties("id", "password","islock");
        example.createCriteria().andEqualTo("userName",userName)
                                .andEqualTo("isDeleted",0);
        return sysUserMapper.selectOneByExample(example);
    }

    @Override
    public Set<String> getUrlPermitByUserName(String loginName) {
        List<String> l = sysUserMapper.getUrlPermitByUserName(loginName);
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
