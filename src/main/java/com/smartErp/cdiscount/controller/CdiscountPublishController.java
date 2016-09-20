package com.smartErp.cdiscount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountCategoryService;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;
import com.smartErp.util.code.JsonUtil;

@Controller
@RequestMapping("cdiscount")
public class CdiscountPublishController {
	
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	@Autowired
	private CdiscountCategoryService cdiscountCategoryService;
	
	@RequestMapping("getShopNameByCreator")
	@ResponseBody
	public String getShopNameByCreator() {
		User user = UserSingleton.getInstance().getUser();
		List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigService.getCdiscountApiConfigByCreator(user.getId());
		Map<String, String> shopNameMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(cdiscountApiConfigList)) {
			for (CdiscountApiConfig cdiscountApiConfig : cdiscountApiConfigList) {
				shopNameMap.put(String.valueOf(cdiscountApiConfig.getId()), cdiscountApiConfig.getShopName());
			}
		}
		return JsonUtil.toJsonStr(shopNameMap);
	}
	
	@RequestMapping("getFirstCdiscountCategory")
	@ResponseBody
	public String getFirstCdiscountCategory(Integer apiId) {
		List<CdiscountCategory> firstCategoryList = cdiscountCategoryService.getFirstCategoryByApiId(apiId);
		if (CollectionUtils.isEmpty(firstCategoryList)) {
			cdiscountCategoryService.getCdiscountCategoryFromCdApi(apiId);
			return getFirstCdiscountCategory(apiId);
		} 
		return JsonUtil.toJsonStr(firstCategoryList);
	}
	
	@RequestMapping("getCdiscountCategoryByParentId")
	@ResponseBody
	public String getCdiscountCategoryByParentId(Integer parentId) {
		List<CdiscountCategory> cdiscountCategoryList = cdiscountCategoryService.getCdiscountCategoryByParentId(parentId);
		return JsonUtil.toJsonStr(cdiscountCategoryList);
	}
	
	public String uploadPublishImage() {
		return null;
	}
	
}
