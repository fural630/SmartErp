package com.smartErp.cdiscount.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.CategoryTree;
import com.google.gson.Gson;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyDate;

public class GetCdiscountCategory {
	
	private MyDate myDate = new MyDate();
	

	
//	public boolean runScript(String argList) { 
//		String email = argList;
//		if (StringUtils.isEmpty(email)) {
//			System.out.println("email is empty !");
//			return false;
//		}
//		CdiscountDao cdiscountDao = new CdiscountDao();
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		queryMap.put("toEmail", email);
//		CdiscountApiConfig cdiscountApiConfig = null;
//		List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountDao.fetchList(queryMap);
//		if (CollectionUtils.isEmpty(cdiscountApiConfigList)) {
//			System.out.println("cdiscountApiConfigList is empty !");
//			return false;
//		} else {
//			cdiscountApiConfig = cdiscountApiConfigList.get(0);
//		}
//		
//		if (null != cdiscountApiConfig) {
//			try {
//				MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
//				MarketplaceAPIServiceStub.GetAllowedCategoryTree parmaGetAllowedCategoryTree = new MarketplaceAPIServiceStub.GetAllowedCategoryTree();
//				String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
//				HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
//				parmaGetAllowedCategoryTree.setHeaderMessage(headerMessage);
//				GetAllowedCategoryTreeResponse response = marketplaceAPIServiceStub.getAllowedCategoryTree(parmaGetAllowedCategoryTree);
//				CategoryTreeMessage categoryTreeMessage = response.getGetAllowedCategoryTreeResult();
//				CategoryTree initCategoryTree = categoryTreeMessage.getCategoryTree();
//				CategoryTree allCategoryTree[] = initCategoryTree.getChildrenCategoryList().getCategoryTree();
//				Integer categoryLevel = 1;
//				CdiscountCategoryDao cdiscountCategoryDao = new CdiscountCategoryDao();
//				Map<String, Object> deleteMap = new HashMap<String, Object>();
//				cdiscountCategoryDao.delete(deleteMap);
//				for (CategoryTree categoryTree : allCategoryTree) {
//					createCdiscountCategory(0, categoryLevel ,categoryTree);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return true;
//	}
	
	public CategoryTree initCdiscountCategoryTree(){
		CategoryTree categoryTree = null;
		String path = "D:\\cdiscount.txt";
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
	
	
//
//	private Integer saveCdiscountCategory(CdiscountCategory cdiscountCategory) {
//		count++;
//		CdiscountCategoryDao cdiscountCategoryDao = new CdiscountCategoryDao();
//		cdiscountCategory.setId(count);
//		cdiscountCategoryDao.saveCategory(cdiscountCategory);
//		return count;
//	}
	
}
