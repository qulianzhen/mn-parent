package com.mn.sysbusinesscode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessException;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import com.mn.sysbusinesscode.entity.param.SysBusinessCodeParam;
import com.mn.sysbusinesscode.entity.po.SysBusinessCode;
import com.mn.sysbusinesscode.mapper.SysBusinessCodeMapper;
import com.mn.sysbusinesscode.service.SysBusinessCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:     SysBusinessCodeServiceImpl.java
 * @Description:   业务流水号的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-08-26 23:52:45
 */

@Service("sysBusinessCodeService")
public class SysBusinessCodeServiceImpl implements SysBusinessCodeService{
 
	@Autowired
	private SysBusinessCodeMapper sysBusinessCodeMapper;
	 
	@Override
	public PageInfo<SysBusinessCode> listPage(PageQuerier<SysBusinessCodeParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<SysBusinessCode>(sysBusinessCodeMapper.listSysBusinessCode(pageQuerierParam.getSearch()));
	}

	@Override
    public List<SysBusinessCode> list(SysBusinessCodeParam param) {
    	return sysBusinessCodeMapper.listSysBusinessCode(param);
    }

	@Override
    @Transactional
    public void insert(SysBusinessCodeParam param) {
		SysBusinessCode sysBusinessCode = PojoConvertUtil.convertPojo(param,SysBusinessCode.class);
		sysBusinessCodeMapper.insertSysBusinessCode(sysBusinessCode);
    }

	@Override
    @Transactional
    public void update(SysBusinessCodeParam param) {
		sysBusinessCodeMapper.updateSysBusinessCode(PojoConvertUtil.convertPojo(param,SysBusinessCode.class));
    }

		
	@Override
	public SysBusinessCode get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return sysBusinessCodeMapper.getSysBusinessCode(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		sysBusinessCodeMapper.deleteSysBusinessCode(ids);
	}

	@Override
	public synchronized String getNextCode(String codeTag) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		String[] timeArray = fmt.format(new Date()).split("-");

		if(StringUtils.isEmpty(codeTag)){
			throw new BusinessException("流水号编码不能为空!");
		}
		SysBusinessCodeParam param = new SysBusinessCodeParam();
		param.setCodeTag(codeTag);
		List<SysBusinessCode> businessCodeList = sysBusinessCodeMapper.listSysBusinessCode(param);
		if(businessCodeList == null || businessCodeList.isEmpty()){
			throw new BusinessException("找不到对应的流水号编码设置!");
		}
		if(businessCodeList.size()>1){
			throw new BusinessException("该编码有多个流水号设置!");
		}
		SysBusinessCode sysBusinessCode = businessCodeList.get(0);

		String returnVal = "";

		Integer currentVal = sysBusinessCode.getCurrentVal();
		String codePrefixion = sysBusinessCode.getCodePrefixion();
		String codeSuffix = sysBusinessCode.getCodeSuffix();
		Integer codeLength = sysBusinessCode.getCodeLength();
		Integer codeType = sysBusinessCode.getCodeType();
		Date updateDate = sysBusinessCode.getUpdateDate();
		String[] updateDateArray = null;
		if(updateDate != null){
			updateDateArray = fmt.format(updateDate).split("-");
		}


		if(codeType == 1 && updateDate!=null &&
				Integer.parseInt(updateDateArray[0]+updateDateArray[1]+updateDateArray[2])!=Integer.parseInt(timeArray[0]+timeArray[1]+timeArray[2])){
			currentVal = 0;
		}
		if(codeType == 2 && updateDate!=null &&
				Integer.parseInt(updateDateArray[0]+updateDateArray[1])!=Integer.parseInt(timeArray[0]+timeArray[1])){
			currentVal = 0;
		}
		if(codeType == 3 && updateDate!=null &&
				Integer.parseInt(updateDateArray[0])!=Integer.parseInt(timeArray[0])){
			currentVal = 0;
		}

		returnVal = (codePrefixion==null?"":codePrefixion) + currentVal + (codeSuffix==null?"":codeSuffix);

		if(codeLength!=0){
			if(returnVal.length() > codeLength){
				throw new RuntimeException("生成的流水号，长度超出设置的总长度!");
			}
			int difference = codeLength - returnVal.length();
			String differenceZero = "";
			for(int i=0,len=difference;i<len;i++){
				differenceZero += "0";
			}
			returnVal = (codePrefixion==null?"":codePrefixion) + differenceZero + currentVal + (codeSuffix==null?"":codeSuffix);
		}

		returnVal = returnVal.replaceAll("(?i)YYYY",timeArray[0])
				.replaceAll("(?i)MM",timeArray[1])
				.replaceAll("(?i)DD",timeArray[2])
				.replaceAll("(?i)HH",timeArray[3])
				.replaceAll("(?i)MI",timeArray[4])
				.replaceAll("(?i)SS",timeArray[5]);
		sysBusinessCodeMapper.updateCurrentVal(currentVal+1,sysBusinessCode.getId());
		return returnVal;
	}
}