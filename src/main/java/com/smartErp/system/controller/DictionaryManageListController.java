package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.system.model.DictionaryType;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.service.DictionaryTypeService;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.JsonUtil;

@Controller
@RequestMapping("system")
public class DictionaryManageListController {
	
	@Autowired
	private DictionaryTypeService dictionaryTypeService;
	
	@RequestMapping("dictionaryManageList")
	public String dictionaryManageList() {
		
		
		return "system/dictionaryManageList";
	}
	
	@RequestMapping("getAllDictionaryTree")
	@ResponseBody
	public String getDictionaryAll() {
		List<Map<String, Object>> tree = dictionaryTypeService.getAllDictionaryTree();
		Dumper.dump(tree);
		return JsonUtil.toJsonStr(tree);
	}
	
	@RequestMapping("getDictionaryById")
	@ResponseBody
	public String getDictionaryById (Integer id) {
		DictionaryType dictionaryType = dictionaryTypeService.getDictionaryById(id);
		return JsonUtil.toJsonStr(dictionaryType);
	}
	
	@RequestMapping("addDictionaryType")
	@ResponseBody
	public String addDictionaryType(Integer id, String moduleName) {
		dictionaryTypeService.addDictionaryType(id, moduleName);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("deleteDictionaryType")
	@ResponseBody
	public String deleteDictionaryType() {
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("addRootDictionaryType")
	@ResponseBody
	public String addRootDictionaryType(Integer id, String moduleName) {
		dictionaryTypeService.addRootDictionaryType(id, moduleName);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
//	@RequestMapping("addRootDictionaryType")
//	@ResponseBody
//	public String addRootDictionaryType (String moduleName) {
//		dictionaryTypeService.addRootDictionaryType(moduleName);
//		ReturnMessage returnMessage = new ReturnMessage();
//		return JsonUtil.toJsonStr(returnMessage);
//	}
}
