package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smartErp.system.service.DictionaryTypeService;
import com.smartErp.util.code.Dumper;

@Controller
@RequestMapping("system")
public class DictionaryManageListController {
	
	@Autowired
	private DictionaryTypeService dictionaryTypeService;
	
	private Gson gson = new Gson();
	
	@RequestMapping("dictionaryManageList")
	public String dictionaryManageList() {
		
		
		return "system/dictionaryManageList";
	}
	
	@RequestMapping("getDictionaryByParentId")
	@ResponseBody
	public String getDictionaryByParentId(Integer id) {
		System.out.println("id = " + id);
		List<Map<String, Object>> tree = dictionaryTypeService.getDictionaryByParentId(0);
		Dumper.dump(tree);
		String treeJson = gson.toJson(tree);
		return treeJson;
	}
	
	@RequestMapping("getDictionaryAll")
	@ResponseBody
	public String getDictionaryAll() {
		List<Map<String, Object>> tree = dictionaryTypeService.getDictionaryAll();
		Dumper.dump(tree);
		String treeJson = gson.toJson(tree);
		return treeJson;
	}
	
	@RequestMapping("getDictionaryById")
	@ResponseBody
	public String getDictionaryById (Integer id) {
		Map<String, Object> node = dictionaryTypeService.getDictionaryById(id);
		Dumper.dump(node);
		return gson.toJson(node);
	}
}
