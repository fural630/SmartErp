package com.smartErp.cdiscount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTree;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTreeMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetAllowedCategoryTreeResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.dao.CdiscountCategoryDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.util.code.MyDate;

@Service
public class CdiscountCategoryService {
	
	@Autowired(required=false)
	private CdiscountCategoryDao cdiscountCategoryDao;
	@Autowired(required=false)
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	
	public void saveCdiscountCategory(CdiscountCategory cdiscountCategory) {
		cdiscountCategoryDao.insert(cdiscountCategory);
	}
	
	public List<CdiscountCategory> getFirstCategory() {
		return cdiscountCategoryDao.getFirstCategory();
	}
	
	public void getCdiscountCategoryFromCdApi(Integer apiId) {
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigDao.getById(apiId);
		if (null != cdiscountApiConfig) {
			try {
				MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
				MarketplaceAPIServiceStub.GetAllowedCategoryTree parmaGetAllowedCategoryTree = new MarketplaceAPIServiceStub.GetAllowedCategoryTree();
				String token = CdiscountTokenUtil.getToken(cdiscountApiConfig);
				HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
				parmaGetAllowedCategoryTree.setHeaderMessage(headerMessage);
				GetAllowedCategoryTreeResponse response = marketplaceAPIServiceStub.getAllowedCategoryTree(parmaGetAllowedCategoryTree);
				CategoryTreeMessage categoryTreeMessage = response.getGetAllowedCategoryTreeResult();
				CategoryTree initCategoryTree = categoryTreeMessage.getCategoryTree();
				CategoryTree allCategoryTree[] = initCategoryTree.getChildrenCategoryList().getCategoryTree();
				Integer categoryLevel = 1;
				for (CategoryTree categoryTree : allCategoryTree) {
					createCdiscountCategory(categoryLevel ,categoryTree, 0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		cdiscountCategoryDao.insert(cdiscountCategory);
		if (null != childCategoryTree && childCategoryTree.length > 0) {
			for (CategoryTree childCategoryTreetmp : childCategoryTree) {
				createCdiscountCategory(categoryLevel + 1, childCategoryTreetmp, cdiscountCategory.getId());
			}
		}
	}

	public List<CdiscountCategory> getCdiscountCategoryByParentId(
			Integer parentId) {
		return cdiscountCategoryDao.getCdiscountCategoryByParentId(parentId);
	}
}
