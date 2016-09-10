package junitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTree;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountCategoryService;
import com.smartErp.cdiscount.service.GetCdiscountCategory;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyDate;

public class CdiscountCategoryTest {
	
	private CdiscountCategoryService cdiscountCategoryService;
	
	
	@Before
	public void before(){                                                                    
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"etc/spring/mysql.xml","etc/spring/applicationContext.xml"});
		cdiscountCategoryService = (CdiscountCategoryService) applicationContext.getBean("cdiscountCategoryService");
	}

	@Test
	public void test() {
//		CdiscountCategory cdiscountCategory = new CdiscountCategory();
//		cdiscountCategoryService.saveCdiscountCategory(cdiscountCategory);
//		Dumper.dump(cdiscountCategory);
		
		getLocalCategory();
	}
	
	
	public void getLocalCategory() {
		GetCdiscountCategory getCdiscountCategory = new GetCdiscountCategory();
		CategoryTree initCategoryTree = getCdiscountCategory.initCdiscountCategoryTree();
		CategoryTree allCategoryTree[] = initCategoryTree.getChildrenCategoryList().getCategoryTree();
		Dumper.dump(allCategoryTree[0]);
		Integer categoryLevel = 1;
		for (CategoryTree categoryTree : allCategoryTree) {
			createCdiscountCategory(categoryLevel ,categoryTree, 0);
		}
	}
	
	public void createCdiscountCategory(Integer categoryLevel, CategoryTree categoryTree, Integer parentId) {
		CdiscountCategory cdiscountCategory = new CdiscountCategory();
		cdiscountCategory.setCategoryCode(categoryTree.getCode());
		cdiscountCategory.setName(categoryTree.getName());
		cdiscountCategory.setCategoryLevel(categoryLevel);
		cdiscountCategory.setParentId(parentId);
		cdiscountCategory.setUpdateTime(new MyDate().getCurrentDateTime());
		CategoryTree childCategoryTree[] = categoryTree.getChildrenCategoryList().getCategoryTree();
		if (null != childCategoryTree && childCategoryTree.length > 0) {
			cdiscountCategory.setIsParent(1);
		} else {
			cdiscountCategory.setIsParent(0);
		}
		cdiscountCategoryService.saveCdiscountCategory(cdiscountCategory);
		if (null != childCategoryTree && childCategoryTree.length > 0) {
			for (CategoryTree childCategoryTreetmp : childCategoryTree) {
				createCdiscountCategory(categoryLevel + 1, childCategoryTreetmp, cdiscountCategory.getId());
			}
		}
	}

}
