package com.smartErp.system.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.application.libraries.constentEnum.OpenCloseEnum;
import com.smartErp.application.libraries.constentEnum.RandomRangeEnum;
import com.smartErp.application.libraries.constentEnum.ScriptTypeEnum;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.code.SystemInfo;
import com.smartErp.system.dao.ScriptConfigDao;
import com.smartErp.system.model.ScriptConfig;
import com.smartErp.util.frame.Page;

@Service
public class ScriptConfigService {
	
	@Autowired(required = false)
	private ScriptConfigDao scriptConfigDao;
	@Autowired(required = false)
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	public List<Map<String, Object>> getScriptConfigPage(Page page) {
		return scriptConfigDao.getScriptConfigPage(page);
	}

	public void createScriptConfig(ScriptConfig scriptConfig) {
		scriptConfigDao.createScriptConfig(scriptConfig);
	}

	public void updateScriptConfig(ScriptConfig scriptConfig) {
		scriptConfigDao.updateScriptConfig(scriptConfig);
	}

	public ScriptConfig getScriptConfigById(Integer id) {
		return scriptConfigDao.getScriptConfigById(id);
	}

	public void deleteScriptConfig(Integer id) {
		scriptConfigDao.deleteScriptConfig(id);
	}

	public List<ScriptConfig> getScriptConfigNoClose() {
		return scriptConfigDao.getScriptConfigByOpenStatus(OpenCloseEnum.OPEN.getValue());
	}

	public boolean createCrontab(String host) {
		String allCrontabStr = "";
		String targetCrondFile = "/var/spool/cron/root";
		System.out.println(targetCrondFile);
		List<ScriptConfig> scriptConfigList = this.getScriptConfigNoClose();
		if (CollectionUtils.isNotEmpty(scriptConfigList)) {
			for (ScriptConfig scriptConfig : scriptConfigList) {
				String crontabStr = "";
				crontabStr += "#" + scriptConfig.getScriptName() + "\n";
				String scriptUrl = scriptConfig.getScriptUrl();
				if (scriptUrl.contains("{host}")) {
					scriptUrl = scriptUrl.replace("{host}", host);
				}
				Integer scriptType = scriptConfig.getScriptType();
				Integer randomRange = scriptConfig.getRandomRange();
				String crontab = scriptConfig.getCrontab();
				
				if (scriptUrl.contains("{apiId}")) {
					List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigDao.getApiConfigByCloseStatus(OpenCloseEnum.OPEN.getValue());
					if (CollectionUtils.isNotEmpty(cdiscountApiConfigList)) {
						for (CdiscountApiConfig cdiscountApiConfig : cdiscountApiConfigList) {
							String cronPrefix = spliceCronPrefix(scriptType, randomRange, crontab);
							String tempScriptUrl = scriptUrl;
							tempScriptUrl = tempScriptUrl.replace("{apiId}", String.valueOf(cdiscountApiConfig.getId()));
							crontabStr += cronPrefix + "\"" +tempScriptUrl +  "\"" + "\n";
						}
					}
				}
				allCrontabStr += crontabStr + "\n";
			}
			System.out.println(allCrontabStr);
			if (StringUtils.isNotEmpty(allCrontabStr)) {
				return writeToCrontab(allCrontabStr, targetCrondFile);
			}
		}
		return false;
	}

	private boolean writeToCrontab(String allCrontabStr, String targetCrondFile) {
		File file = new File(targetCrondFile);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(allCrontabStr);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private String spliceCronPrefix(Integer scriptType, Integer randomRange, String crontab) {
		String cronPrefix = "";
		if (scriptType.equals(ScriptTypeEnum.COSTOMIZE.getValue())) {
			cronPrefix += crontab;
		} else {
			if (null != randomRange) {
				String randomNum  = "";
				if (randomRange.equals(RandomRangeEnum.ONE_TO_FIVE.getValue())) {
					randomNum = getRandomNum(1, 5);
				} else if (randomRange.equals(RandomRangeEnum.ONE_TO_TEN.getValue())) {
					randomNum = getRandomNum(1, 10);
				} else if (randomRange.equals(RandomRangeEnum.ONE_TO_FIFTEEN.getValue())) {
					randomNum = getRandomNum(1, 15);
				} else if (randomRange.equals(RandomRangeEnum.SIXTEEN_TO_THIRTY.getValue())) {
					randomNum = getRandomNum(16, 30);
				} else if (randomRange.equals(RandomRangeEnum.THIRTYONE_TO_FORTYFIVE.getValue())) {
					randomNum = getRandomNum(31, 45);
				} else if (randomRange.equals(RandomRangeEnum.FORTYSIX_TO_FIFTYNINE.getValue())) {
					randomNum = getRandomNum(46, 59);
				} else if (randomRange.equals(RandomRangeEnum.ZERO_TO_FIFTYNINE.getValue())) {
					randomNum = getRandomNum(0, 59);
				}
				if (scriptType.equals(ScriptTypeEnum.EVER_RANDOM_NUMBER.getValue())) {		//每隔随机数
					crontab = "*/" + randomNum + " " + crontab;
				} else if (scriptType.equals(ScriptTypeEnum.SPECIFY_RANDOM_NUMBER.getValue())) {
					crontab = randomNum + " " + crontab;
				}
				cronPrefix += crontab;
			}
		}
		cronPrefix += " curl ";
		return cronPrefix;
	}
	
	private String getRandomNum(int max, int min) {
		return String.valueOf(Math.round(Math.random() * (max - min) + min));
	}
	
}
