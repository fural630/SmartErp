package com.smartErp.cdiscount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.system.model.User;
import com.smartErp.util.code.JsonUtil;

@Controller
@RequestMapping("cdiscount")
public class CdiscountPublishController {
	
	@Autowired
	CdiscountApiConfigService cdiscountApiConfigService;
	
	@RequestMapping("getShopNameByCreator")
	@ResponseBody
	public String getShopNameByCreator(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<CdiscountApiConfig> cdiscountApiConfigList = cdiscountApiConfigService.getCdiscountApiConfigByCreator(user.getId());
		Map<String, String> shopNameMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(cdiscountApiConfigList)) {
			for (CdiscountApiConfig cdiscountApiConfig : cdiscountApiConfigList) {
				shopNameMap.put(String.valueOf(cdiscountApiConfig.getId()), cdiscountApiConfig.getShopName());
			}
		}
		return JsonUtil.toJsonStr(shopNameMap);
	}
	
}
