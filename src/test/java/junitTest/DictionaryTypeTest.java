package junitTest;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartErp.system.model.DictionaryType;
import com.smartErp.system.service.DictionaryTypeService;
import com.smartErp.util.code.Dumper;

public class DictionaryTypeTest {
	
	private DictionaryTypeService dictionaryTypeService;
	
	@Before
	public void before(){                                                                    
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"etc/spring/mysql.xml","etc/spring/applicationContext.xml"});
		dictionaryTypeService = (DictionaryTypeService) applicationContext.getBean("dictionaryTypeService");
	}

	@Test
	public void test() {
		 List<DictionaryType> tree = dictionaryTypeService.getDictionaryByParentId(0);
		 Dumper.dump(tree);
	}

}
