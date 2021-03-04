package com.mn.doubleball.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessException;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.doubleball.entity.param.DoubleBallParam;
import com.mn.doubleball.entity.po.DoubleBall;
import com.mn.doubleball.mapper.DoubleBallMapper;
import com.mn.doubleball.service.DoubleBallService;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.module.page.PageQuerier;
import com.mn.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName:     DoubleBallServiceImpl.java
 * @Description:   双色球历史记录的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-21 01:22:54
 */

@Service("doubleBallService")
public class DoubleBallServiceImpl implements DoubleBallService{
 
	@Autowired
	private DoubleBallMapper doubleBallMapper;
	 
	@Override
	public PageInfo<DoubleBall> listPage(PageQuerier<DoubleBallParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<DoubleBall>(doubleBallMapper.listDoubleBall(pageQuerierParam.getSearch()));
	}

	@Override
    public List<DoubleBall> list(DoubleBallParam param) {
    	return doubleBallMapper.listDoubleBall(param);
    }

	@Override
    @Transactional
    public void insert(DoubleBallParam param) {
		DoubleBall doubleBall = PojoConvertUtil.convertPojo(param,DoubleBall.class);
		doubleBallMapper.insertDoubleBall(doubleBall);
    }

	@Override
    @Transactional
    public void update(DoubleBallParam param) {
		doubleBallMapper.updateDoubleBall(PojoConvertUtil.convertPojo(param,DoubleBall.class));
    }

		
	@Override
	public DoubleBall get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return doubleBallMapper.getDoubleBall(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		doubleBallMapper.deleteDoubleBall(ids);
	}

	@Override
	public List<Map<Integer, Object>> singleBallAnalyse(Integer dbBlue) {
		BigDecimal b100 = new BigDecimal(100);
		List<Map<Integer, Object>> resultList = new ArrayList<>();
		List<Map<String,Object>> queryResult = doubleBallMapper.queryNextNoBlue(dbBlue);
		if(queryResult == null){
			return resultList;
		}
		BigDecimal sumCount = BigDecimal.ZERO;
		for(Map<String,Object> currentMap : queryResult){
			sumCount = sumCount.add(new BigDecimal(currentMap.get("dbBlueCount").toString()));
		}
		Map<Integer, Object> newMap;
		for(Map<String,Object> currentMap : queryResult){
			newMap = new HashMap<>();
			newMap.put(Integer.parseInt(currentMap.get("dbBlue").toString()),
					new BigDecimal(currentMap.get("dbBlueCount").toString()).divide(sumCount,4,BigDecimal.ROUND_HALF_UP).multiply(b100));
			resultList.add(newMap);
		}
		return resultList;
	}

	@Override
	public Map<String,Object> getSimilarity(Integer range) {
		if(range == null || range.intValue()<3 || range.intValue()>10){
			throw new BusinessException("3~10个球！");
		}
		Map<String,Object> resultMap = new HashMap<>();
		//这里采用循环比较法，在java内存中比较
		List<DoubleBall> allList = list(new DoubleBallParam());
		int allLen = allList.size();
		List<DoubleBall> targetList = new ArrayList<>(allList.subList(0,range));
		List<DoubleBall> newtargetList;
		List<List<DoubleBall>> matchList = new ArrayList<>();
		List<DoubleBall> currentList;
		for(int i=1,len=allLen-range+1;i<len;i++){
			currentList = new ArrayList<>(allList.subList(i,i+range));
			if(firstMatch(currentList,targetList)){
				matchList.add(currentList);
			}else if(secondMatch(currentList,targetList)){
				matchList.add(currentList);
			}else if(threeMatch(currentList,targetList)){
				matchList.add(currentList);
			}
		}
		newtargetList = DataUtil.deepCopy(targetList);
		Collections.reverse(newtargetList);
		for(List<DoubleBall> currMatch : matchList){
			Collections.reverse(currMatch);
		}

		resultMap.put("targetList",newtargetList);
		resultMap.put("matchList",matchList);
		return resultMap;
	}

	/**
	 * 完全匹配
	 * @param currentList
	 * @param targetList
	 * @return
	 */
	private boolean firstMatch(List<DoubleBall> currentList, List<DoubleBall> targetList) {
		for(int i=0,len=currentList.size();i<len;i++){
			if(currentList.get(i).getDbBlue().intValue() != targetList.get(i).getDbBlue().intValue()){
				return false;
			}
		}
		return true;
	}


	/**
	 * 上/下移匹配
	 * @param currentList
	 * @param targetList
	 * @return
	 */
	private boolean secondMatch(List<DoubleBall> currentList, List<DoubleBall> targetList) {
		int oneDiffVal = 0;
		for(int i=0,len=currentList.size();i<len;i++){
			if(i==0){
				oneDiffVal = currentList.get(i).getDbBlue() - targetList.get(i).getDbBlue();
			}
			if(oneDiffVal != currentList.get(i).getDbBlue() - targetList.get(i).getDbBlue()){
				return false;
			}
		}
		return true;
	}

	/**
	 * 区间法-将篮球范围分为1-3,4-6,7-9,10-12,13-16 五个区间
	 * @param currentList
	 * @param targetList
	 * @return
	 */
	private boolean threeMatch(List<DoubleBall> currentList, List<DoubleBall> targetList) {
		int currentBlue;
		int targetBlue;
		for(int i=0,len=currentList.size();i<len;i++){
			currentBlue = currentList.get(i).getDbBlue().intValue();
			targetBlue = targetList.get(i).getDbBlue().intValue();
			if(currentBlue>=1 && currentBlue<=3 && targetBlue>3){
				return false;
			}else if(currentBlue>=4 && currentBlue<=6 && (targetBlue<4 || targetBlue>6)){
				return false;
			}else if(currentBlue>=7 && currentBlue<=9 && (targetBlue<7 || targetBlue>9)){
				return false;
			}else if(currentBlue>=10 && currentBlue<=12 && (targetBlue<10 || targetBlue>12)){
				return false;
			}else if(currentBlue>=13 && currentBlue<=16 && (targetBlue<13 || targetBlue>16)){
				return false;
			}
		}
		return true;
	}


}