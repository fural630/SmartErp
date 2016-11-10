package com.smartErp.cdiscount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smartErp.application.libraries.constentEnum.YesNoEnum;
import com.smartErp.application.libraries.select.YesNo;
import com.smartErp.cdiscount.model.CdiscountDefaultsValue;
import com.smartErp.cdiscount.model.DefaultsDeliveryMode;
import com.smartErp.cdiscount.model.PublishDeliveryMode;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountDefaultsValueService;
import com.smartErp.cdiscount.service.DefaultsDeliveryModeService;
import com.smartErp.code.MainPage;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.enumerate.ReturnMessageEnum;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountDefaultsValueController extends MainPage{
	
	@Autowired
	private CdiscountDefaultsValueService cdiscountDefaultsValueService;
	@Autowired
	private DefaultsDeliveryModeService defaultsDeliveryModeService;
	
	
	@RequestMapping("cdiscountDefaultsValue")
	public String cdiscountDefaultsValue (Model model, Page page){
		String title = "navigator.cdiscount.defaults.value";
		_execute(page, model, title);
		List<Map<String, Object>> collection = cdiscountDefaultsValueService.getCdiscountDefaultsValueByPage(page);
		for (Map<String, Object> map : collection) {
			Integer id = Integer.parseInt(map.get("id").toString());
			List<DefaultsDeliveryMode> defaultsDeliveryModeList = defaultsDeliveryModeService.getDefaultsValueDeliveryModeListByDefaultsId(id);
			map.put("defaultsDeliveryModeList", defaultsDeliveryModeList);
		}
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountDefaultsValue";
	}
	
	@RequestMapping("saveCdiscountDefaultsValue")
	@ResponseBody
	public String saveCdiscountDefaultsValue (CdiscountDefaultsValue cdiscountDefaultsValue, @RequestParam("defaultsValueDeliveryModeList") String defaultsValueDeliveryModeStr) {
		Gson gson = new Gson();
		ReturnMessage returnMessage = new ReturnMessage();
		User user = UserSingleton.getInstance().getUser();
		List defaultsValueDeliveryModeList = gson.fromJson(defaultsValueDeliveryModeStr, List.class);
		if (null == cdiscountDefaultsValue.getId()) {		//create
			boolean flag = cdiscountDefaultsValueService.isHaveDefaultsTemplate(user.getId());
			if (flag) {
				cdiscountDefaultsValue.setIsDefaults(YesNoEnum.NO.getValue());
			} else {
				cdiscountDefaultsValue.setIsDefaults(YesNoEnum.YES.getValue());
			}
			cdiscountDefaultsValue.setCreator(user.getId());
			cdiscountDefaultsValueService.insertCdiscountDefaultsValue(cdiscountDefaultsValue);
			if (CollectionUtils.isNotEmpty(defaultsValueDeliveryModeList)) {
				defaultsDeliveryModeService.deleteDefaultsValueDeliveryModeByDefaultsId(cdiscountDefaultsValue.getId());
				for (Object object : defaultsValueDeliveryModeList) {
					DefaultsDeliveryMode defaultsDeliveryMode = (DefaultsDeliveryMode) gson.fromJson(gson.toJson(object), DefaultsDeliveryMode.class);
					defaultsDeliveryMode.setDefaultsId(cdiscountDefaultsValue.getId());
					defaultsDeliveryModeService.insertDefaultsValueDeliveryMode(defaultsDeliveryMode);
				}
			}
		} else {
			defaultsDeliveryModeService.deleteDefaultsValueDeliveryModeByDefaultsId(cdiscountDefaultsValue.getId());
			if (CollectionUtils.isNotEmpty(defaultsValueDeliveryModeList)) {
				for (Object object : defaultsValueDeliveryModeList) {
					DefaultsDeliveryMode defaultsDeliveryMode = (DefaultsDeliveryMode) gson.fromJson(gson.toJson(object), DefaultsDeliveryMode.class);
					defaultsDeliveryMode.setDefaultsId(cdiscountDefaultsValue.getId());
					defaultsDeliveryModeService.insertDefaultsValueDeliveryMode(defaultsDeliveryMode);
				}
			}
			cdiscountDefaultsValueService.udpateCdiscountDefaultsValue(cdiscountDefaultsValue);
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("deleteCdiscountDefaultsValue")
	@ResponseBody
	public String deleteCdiscountDefaultsValue (Integer id) {
		cdiscountDefaultsValueService.deleteCdiscountDefaultsValueById(id);
		defaultsDeliveryModeService.deleteDefaultsValueDeliveryModeByDefaultsId(id);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("getCdiscountDefaultsValueById")
	@ResponseBody
	public String getCdiscountDefaultsValueById (Integer id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CdiscountDefaultsValue cdiscountDefaultsValue = cdiscountDefaultsValueService.getCdiscountDefaultsValueById(id);
		List<DefaultsDeliveryMode> defaultsDeliveryModeList = defaultsDeliveryModeService.getDefaultsValueDeliveryModeListByDefaultsId(id);
		resultMap.put("cdiscountDefaultsValue", cdiscountDefaultsValue);
		resultMap.put("defaultsDeliveryModeList", defaultsDeliveryModeList);
		return JsonUtil.toJsonStr(resultMap);
	}
	
	@RequestMapping("setAsDefaultsTemplate")
	@ResponseBody
	public String setAsDefaultsTemplate (Integer id) {
		ReturnMessage returnMessage = new ReturnMessage();
		User user = UserSingleton.getInstance().getUser();
		cdiscountDefaultsValueService.updateIsDefaultsTemplate(YesNoEnum.NO.getValue(), user.getId());
		cdiscountDefaultsValueService.setAsDefaultsTemplate(id);
		return JsonUtil.toJsonStr(returnMessage);
	}
}
