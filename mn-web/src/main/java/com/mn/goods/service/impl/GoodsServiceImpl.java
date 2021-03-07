package com.mn.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mn.commonbean.exception.BusinessInvalidParamException;
import com.mn.config.MnSystemConfig;
import com.mn.goods.entity.param.GoodsParam;
import com.mn.goods.entity.po.Goods;
import com.mn.goods.mapper.GoodsMapper;
import com.mn.goods.service.GoodsService;
import com.mn.mnutil.DateUtil;
import com.mn.mnutil.FileUtil;
import com.mn.mnutil.PojoConvertUtil;
import com.mn.mnutil.StringUtil;
import com.mn.module.page.PageQuerier;
import com.mn.sysbusinesscode.service.SysBusinessCodeService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:     GoodsServiceImpl.java
 * @Description:   商品管理的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2021-03-05 00:18:38
 */

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
 
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private SysBusinessCodeService sysBusinessCodeService;
	 
	@Override
	public PageInfo<Goods> listPage(PageQuerier<GoodsParam> pageQuerierParam) {
		PageHelper.startPage(pageQuerierParam.getPage(),pageQuerierParam.getRows());
        return new PageInfo<Goods>(goodsMapper.listGoods(pageQuerierParam.getSearch()));
	}

	@Override
    public List<Goods> list(GoodsParam param) {
    	return goodsMapper.listGoods(param);
    }

	@Override
    @Transactional
    public void insert(GoodsParam param) {
		Goods goods = PojoConvertUtil.convertPojo(param,Goods.class);
		goodsMapper.insertGoods(goods);
    }

	@Override
    @Transactional
    public void update(GoodsParam param) {
		goodsMapper.updateGoods(PojoConvertUtil.convertPojo(param,Goods.class));
    }

		
	@Override
	public Goods get(Long id) {
	
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return goodsMapper.getGoods(id);
	}
		
	@Override
	public void delete(List<Long> ids) {
	
		if(ids == null || ids.isEmpty()) {
			throw new BusinessInvalidParamException("参数有误");
		}
		goodsMapper.deleteGoods(ids);
	}

	@Override
	public List<String> fileUpload(MultipartFile[] multipartFiles, GoodsParam param) {
		if(multipartFiles == null){
			throw new BusinessInvalidParamException("图片不存在!");
		}
		if(param == null || StringUtil.isEmpty(param.getGoodsNo())){
			throw new BusinessInvalidParamException("商品基本信息不存在!");
		}

		List<String> imgPathList = new ArrayList<>();
		Arrays.stream(multipartFiles).forEach(e->{
			if(e == null || e.isEmpty()){
				throw new BusinessInvalidParamException("图片为空!");
			}
			String fileName = e.getOriginalFilename();  // 文件名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
			if(!suffixName.equalsIgnoreCase(".JPG")
					&&!suffixName.equalsIgnoreCase(".JPEG")
			        &&!suffixName.equalsIgnoreCase(".PNG")){
				throw new BusinessInvalidParamException("只支持jpg，jpeg，png格式!");
			}
			String currentM = DateUtil.getCurrentDate("yyyyMM");
			String newFileName = System.currentTimeMillis() + RandomStringUtils.random(4,"abcdefghigklmnopqrstuvwxyz") + suffixName;
			String filePath = MnSystemConfig.uploadTarget + currentM + File.separator;
			File file = FileUtil.createFile(filePath + newFileName);
			try {
				e.transferTo(file);
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
			imgPathList.add(currentM + File.separator +  newFileName);
		});
		param.setGoodsImg1(imgPathList.get(0));
		param.setGoodsImg2(imgPathList.get(1));
		if(param.getId() == null){
			goodsMapper.insertGoods(PojoConvertUtil.convertPojo(param,Goods.class));
		}else{
			goodsMapper.updateGoods(PojoConvertUtil.convertPojo(param,Goods.class));
		}

		return imgPathList;
	}

	@Override
	public String getNewGoodsNo() {
		return sysBusinessCodeService.getNextCode("SPBH");
	}
}