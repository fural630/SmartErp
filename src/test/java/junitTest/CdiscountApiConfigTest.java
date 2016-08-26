package junitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;

public class CdiscountApiConfigTest {
	
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	
	@Before
	public void before(){                                                                    
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"etc/spring/mysql.xml","etc/spring/applicationContext.xml"});
		cdiscountApiConfigService = (CdiscountApiConfigService) applicationContext.getBean("cdiscountApiConfigService");
	}
	
	@Test
	public void test() {
		testInsert();
//		testUpdate();

	}

	private void testUpdate() {
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setId(1);
		cdiscountApiConfig.setApiAccount("testAccount1");
		cdiscountApiConfig.setApiPassword("pass1");
		cdiscountApiConfig.setEmail("3126111");
		cdiscountApiConfig.setLastUpdateTime("2016-12-01 05:05:11");
		cdiscountApiConfig.setReceivablesEmail("32132Q11");
		cdiscountApiConfig.setShopName("tomtop11");
		cdiscountApiConfig.setSystemLog("log1111");
		cdiscountApiConfigService.updateCdiscountApiConfige(cdiscountApiConfig);
		
	}

	private void testInsert() {
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setApiAccount("testAccount");
		cdiscountApiConfig.setCreator(630);
		cdiscountApiConfig.setEmail("3126");
		cdiscountApiConfig.setLastUpdateTime("2016-12-01 05:05:00");
		cdiscountApiConfig.setReceivablesEmail("32132Q");
		cdiscountApiConfig.setShopName("tomtop");
		cdiscountApiConfig.setSystemLog("log1");
		cdiscountApiConfigService.insertCdiscountApiConfig(cdiscountApiConfig);
	}

}
