package com.smartErp.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.code.MainPage;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.enumerate.ReturnMessageEnum;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.ScriptConfig;
import com.smartErp.system.model.User;
import com.smartErp.system.service.ScriptConfigService;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("system")
public class ScriptConfigManageController extends MainPage {
	
	@Autowired
	private ScriptConfigService scriptConfigService;
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	
	@RequestMapping("scriptConfigManage")
	public String scriptManage (Model model, Page page){
		_execute(page, model);
		List<Map<String, Object>> collection = scriptConfigService.getScriptConfigPage(page);
		model.addAttribute("collection", collection);
		return "system/scriptConfigManage";
	}
	
	@RequestMapping("saveScriptConfig")
	@ResponseBody
	public String saveScriptConfig(ScriptConfig scriptConfig) {
		ReturnMessage returnMessage = new ReturnMessage();
		MyDate myDate = new MyDate();
		String currentTime = myDate.getCurrentDateTime();
		scriptConfig.setUpdateTime(currentTime);
		if (scriptConfig.getId() == null) {		//create
			User user = UserSingleton.getInstance().getUser();
			scriptConfig.setCreatorId(user.getId());
			scriptConfig.setCreateTime(currentTime);
			scriptConfigService.createScriptConfig(scriptConfig);
		} else {
			scriptConfigService.updateScriptConfig(scriptConfig);
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("editScriptConfig")
	@ResponseBody
	public String editScriptConfig (Integer id) {
		ScriptConfig scriptConfig = scriptConfigService.getScriptConfigById(id);
		return JsonUtil.toJsonStr(scriptConfig);
	}
	
	
	@RequestMapping("deleteScriptConfig")
	@ResponseBody
	public String deleteScriptConfig (Integer id) {
		scriptConfigService.deleteScriptConfig(id);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("createCrontab")
	@ResponseBody
	public String createCrontab (HttpServletRequest request) {
		String host = request.getServerName();
		ReturnMessage returnMessage = new ReturnMessage();
		boolean flag = scriptConfigService.createCrontab(host);
		if (!flag) {
			returnMessage.setStatus(ReturnMessageEnum.FAIL.getValue());
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
}
