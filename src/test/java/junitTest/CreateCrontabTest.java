package junitTest;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartErp.application.libraries.constentEnum.OpenCloseEnum;
import com.smartErp.application.libraries.constentEnum.RandomRangeEnum;
import com.smartErp.application.libraries.constentEnum.ScriptTypeEnum;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.SystemInfo;
import com.smartErp.system.model.ScriptConfig;
import com.smartErp.system.service.ScriptConfigService;

public class CreateCrontabTest {
	
	private ScriptConfigService scriptConfigService;
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	@Before
	public void before(){                                                                    
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"etc/spring/mysql.xml","etc/spring/applicationContext.xml"});
		scriptConfigService = (ScriptConfigService) applicationContext.getBean("scriptConfigService");
		cdiscountApiConfigService = (CdiscountApiConfigService) applicationContext.getBean("cdiscountApiConfigService");
	}
	
	@Test
	public void test() {
		String host  = "192.168.0.233";
		String allCrontabStr = "";
		List<ScriptConfig> scriptConfigList = scriptConfigService.getScriptConfigNoClose();
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
					List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigService.getApiConfigByCloseStatus(OpenCloseEnum.OPEN.getValue());
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
		}
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
