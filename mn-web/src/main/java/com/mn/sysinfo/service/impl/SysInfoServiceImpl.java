package com.mn.sysinfo.service.impl;

import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mn.sysinfo.entity.vo.SysInfoVo;
import com.mn.sysinfo.service.SysInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:     SysBusinessCodeServiceImpl.java
 * @Description:   系统信息的服务实现
 * 
 * @author         qlz
 * @version        1.0
 * @Date           2020-10-09 23:52:45
 */

@Service("sysInfoService")
public class SysInfoServiceImpl implements SysInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysInfoServiceImpl.class);
 	private String domainName = "http://localhost:54001";

	@Override
	public SysInfoVo get() {
		SysInfoVo vo = new SysInfoVo();
		String envStr = HttpRequest.get(domainName+"/actuator/env").timeout(20000).execute().body();
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> envMap = null;
		try {
			envMap = mapper.readValue(envStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		List<Map<String,Object>> propertySourcesList = (List<Map<String, Object>>) envMap.get("propertySources");
			for(Map<String,Object> itemMap : propertySourcesList){
				if("systemProperties".equals(itemMap.get("name"))){
					Map<String,Object> propertiesMap = (Map<String, Object>) itemMap.get("properties");
					vo.setOsName((String) ((Map<String,Object>)propertiesMap.get("os.name")).get("value"));
					vo.setOsArch((String) ((Map<String,Object>)propertiesMap.get("os.arch")).get("value"));
					vo.setUserName((String) ((Map<String,Object>)propertiesMap.get("user.name")).get("value"));
					vo.setUserLan((String) ((Map<String,Object>)propertiesMap.get("user.language")).get("value"));
					vo.setUserTimeZone((String) ((Map<String,Object>)propertiesMap.get("user.timezone")).get("value"));
					vo.setUserHome((String) ((Map<String,Object>)propertiesMap.get("user.home")).get("value"));

					vo.setJavaVersion((String) ((Map<String,Object>)propertiesMap.get("java.version")).get("value"));
					vo.setJavaHome((String) ((Map<String,Object>)propertiesMap.get("java.home")).get("value"));
					vo.setJavaHome((String) ((Map<String,Object>)propertiesMap.get("java.home")).get("value"));
					vo.setJavaVmMode((String) ((Map<String,Object>)propertiesMap.get("java.vm.info")).get("value"));
					vo.setJavaVmName((String) ((Map<String,Object>)propertiesMap.get("java.vm.name")).get("value"));
					break;
				}
			}

		String runMaxMemStr = HttpRequest.get(domainName+"/actuator/metrics/jvm.memory.max").timeout(20000).execute().body();
		Map<String,Object> maxMemMap = null;
		try {
			maxMemMap = mapper.readValue(runMaxMemStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		vo.setTomcatMaxMem(new BigDecimal(
				((List<Map<String,Object>>)maxMemMap.get("measurements")).get(0).get("value").toString()
		).toPlainString());


		String runUseMemStr = HttpRequest.get(domainName+"/actuator/metrics/jvm.memory.used").timeout(20000).execute().body();
		Map<String,Object> useMemMap = null;
		try {
			useMemMap = mapper.readValue(runUseMemStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		vo.setTomcatUsageMem(new BigDecimal(
				((List<Map<String,Object>>)useMemMap.get("measurements")).get(0).get("value").toString()
		).toPlainString());


		String runUpStr = HttpRequest.get(domainName+"/actuator/metrics/process.uptime").timeout(20000).execute().body();
		Map<String,Object> upMap = null;
		try {
			upMap = mapper.readValue(runUpStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		vo.setTomcatUptime(((List<Map<String,Object>>)upMap.get("measurements")).get(0).get("value").toString());

		String runMaxThStr = HttpRequest.get(domainName+"/actuator/metrics/tomcat.threads.config.max").timeout(20000).execute().body();
		Map<String,Object> maxThMap = null;
		try {
			maxThMap = mapper.readValue(runMaxThStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		vo.setTomcatMaxThreadCount(new BigDecimal(
				((List<Map<String,Object>>)maxThMap.get("measurements")).get(0).get("value").toString()
		).setScale(0).toPlainString());

		String runUseThStr = HttpRequest.get(domainName+"/actuator/metrics/tomcat.threads.current").timeout(20000).execute().body();
		Map<String,Object> useThMap = null;
		try {
			useThMap = mapper.readValue(runUseThStr,Map.class);
		} catch (IOException e) {
			LOGGER.error("格式化json出错",e);
		}
		vo.setTomcatUsageThreadCount(new BigDecimal(
				((List<Map<String,Object>>)useThMap.get("measurements")).get(0).get("value").toString()
		).setScale(0).toPlainString());

		return vo;
	}
		
}