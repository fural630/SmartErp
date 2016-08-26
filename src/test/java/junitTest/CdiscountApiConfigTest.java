package junitTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.code.Dumper;

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
//		testGetById();
//		testGetAll();
	}

	private void testGetAll() {
		List<CdiscountApiConfig> cdiscountApiConfigs = cdiscountApiConfigService.getAllCdiscountApiConfig();
		System.out.println(cdiscountApiConfigs.size());
	}

	private void testGetById() {
		Integer id = 2;
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigService.getCdiscountApiConfigById(id);
		String pass = DESEncrypt.DataDecrypt(cdiscountApiConfig.getApiPassword());
		System.out.println(pass);
	}

	private void testUpdate() {
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setId(1);
		cdiscountApiConfig.setApiAccount("SalesNEmma201623-api");
		cdiscountApiConfig.setApiPassword("9636");
		cdiscountApiConfig.setEmail("cds@tomtop.com");
		cdiscountApiConfig.setLastUpdateTime("2016-12-01 05:05:11");
		cdiscountApiConfig.setReceivablesEmail("cds@tomtop.com");
		cdiscountApiConfig.setShopName("Dealpark");
		cdiscountApiConfig.setSystemLog("log1111");
		cdiscountApiConfigService.updateCdiscountApiConfige(cdiscountApiConfig);
		
	}

	private void testInsert() {
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setApiAccount("SalesNEmma201623-api");
		cdiscountApiConfig.setApiPassword("9636");
		cdiscountApiConfig.setEmail("cds@tomtop.com");
		cdiscountApiConfig.setLastUpdateTime("2016-12-01 05:05:11");
		cdiscountApiConfig.setReceivablesEmail("cds@tomtop.com");
		cdiscountApiConfig.setShopName("Dealpark");
		cdiscountApiConfig.setSystemLog("log1111");
		cdiscountApiConfigService.insertCdiscountApiConfig(cdiscountApiConfig);
	}

}
