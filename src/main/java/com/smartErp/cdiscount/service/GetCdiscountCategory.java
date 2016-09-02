package com.smartErp.cdiscount.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTree;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTreeMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetAllowedCategoryTreeResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.google.gson.Gson;
import com.tomtop.application.dao.mongo.sale.cdiscount.CdiscountCategoryDao;
import com.tomtop.application.dao.mongo.sale.cdiscount.CdiscountDao;
import com.tomtop.application.libraries.MyDate;
import com.tomtop.application.orm.mongo.sale.cdiscount.CdiscountApiConfig;
import com.tomtop.application.orm.mongo.sale.cdiscount.CdiscountCategory;
import com.tomtop.script.cdiscount.CdiscountHeaderMessageUtil;
import com.tomtop.script.cdiscount.CdiscountTokenUtil;

public class GetCdiscountCategory {
	
	private MyDate myDate = new MyDate();
	private static Integer count = 0;
	
	public static void main(String[] args) {
		GetCdiscountCategory getCdiscountCategory = new GetCdiscountCategory();
		getCdiscountCategory.getLocalCategory();
	}
	
	public void getLocalCategory() {
		GetCdiscountCategory getCdiscountCategory = new GetCdiscountCategory();
		CategoryTree initCategoryTree = getCdiscountCategory.initCdiscountCategoryTree();
		CategoryTree allCategoryTree[] = initCategoryTree.getChildrenCategoryList().getCategoryTree();
		Integer categoryLevel = 1;
		CdiscountCategoryDao cdiscountCategoryDao = new CdiscountCategoryDao();
		Map<String, Object> deleteMap = new HashMap<String, Object>();
		cdiscountCategoryDao.delete(deleteMap);
		for (CategoryTree categoryTree : allCategoryTree) {
			createCdiscountCategory(0, categoryLevel ,categoryTree);
		}
	}
	
	public boolean runScript(String argList) { 
		String email = argList;
		if (StringUtils.isEmpty(email)) {
			System.out.println("email is empty !");
			return false;
		}
		CdiscountDao cdiscountDao = new CdiscountDao();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("toEmail", email);
		CdiscountApiConfig cdiscountApiConfig = null;
		List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountDao.fetchList(queryMap);
		if (CollectionUtils.isEmpty(cdiscountApiConfigList)) {
			System.out.println("cdiscountApiConfigList is empty !");
			return false;
		} else {
			cdiscountApiConfig = cdiscountApiConfigList.get(0);
		}
		
		if (null != cdiscountApiConfig) {
			try {
				MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
				MarketplaceAPIServiceStub.GetAllowedCategoryTree parmaGetAllowedCategoryTree = new MarketplaceAPIServiceStub.GetAllowedCategoryTree();
				String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
				HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
				parmaGetAllowedCategoryTree.setHeaderMessage(headerMessage);
				GetAllowedCategoryTreeResponse response = marketplaceAPIServiceStub.getAllowedCategoryTree(parmaGetAllowedCategoryTree);
				CategoryTreeMessage categoryTreeMessage = response.getGetAllowedCategoryTreeResult();
				CategoryTree initCategoryTree = categoryTreeMessage.getCategoryTree();
				CategoryTree allCategoryTree[] = initCategoryTree.getChildrenCategoryList().getCategoryTree();
				Integer categoryLevel = 1;
				CdiscountCategoryDao cdiscountCategoryDao = new CdiscountCategoryDao();
				Map<String, Object> deleteMap = new HashMap<String, Object>();
				cdiscountCategoryDao.delete(deleteMap);
				for (CategoryTree categoryTree : allCategoryTree) {
					createCdiscountCategory(0, categoryLevel ,categoryTree);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public CategoryTree initCdiscountCategoryTree(){
		CategoryTree categoryTree = null;
		String path = "/home/tomtop2028/cdiscount.txt";
		File file = new File(path);
		BufferedReader reader = null;
		try {
			String tempString = null;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while ((tempString = reader.readLine()) != null) {
				if (StringUtils.isNotEmpty(tempString)) {
					categoryTree = new Gson().fromJson(tempString, CategoryTree.class);
				}
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return categoryTree;
	} 
	
	private void createCdiscountCategory(Integer parentId, Integer categoryLevel,
			CategoryTree categoryTree) {
		CdiscountCategory cdiscountCategory = new CdiscountCategory();
		cdiscountCategory.setCategoryCode(categoryTree.getCode());
		cdiscountCategory.setName(categoryTree.getName());
		cdiscountCategory.setCategoryLevel(categoryLevel);
		cdiscountCategory.setAllowOfferIntegration(categoryTree.getAllowOfferIntegration());
		cdiscountCategory.setAllowProductIntegration(categoryTree.getAllowProductIntegration());
		cdiscountCategory.setParentId(parentId);
		cdiscountCategory.setUpdateTime(myDate.getCurrentDateTime());
		CategoryTree childCategoryTree[] = categoryTree.getChildrenCategoryList().getCategoryTree();
		if (null != childCategoryTree && childCategoryTree.length > 0) {
			cdiscountCategory.setIsParent(1);
		} else {
			cdiscountCategory.setIsParent(0);
		}
		Integer saveCategoryId = saveCdiscountCategory(cdiscountCategory);
		if (null != childCategoryTree && childCategoryTree.length > 0) {
			for (CategoryTree childCategoryTreetmp : childCategoryTree) {
				createCdiscountCategory(saveCategoryId, categoryLevel + 1, childCategoryTreetmp);
			}
		}
	}

	private Integer saveCdiscountCategory(CdiscountCategory cdiscountCategory) {
		count++;
		CdiscountCategoryDao cdiscountCategoryDao = new CdiscountCategoryDao();
		cdiscountCategory.setId(count);
		cdiscountCategoryDao.saveCategory(cdiscountCategory);
		return count;
	}
	
}
