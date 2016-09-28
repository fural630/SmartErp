package com.smartErp.cdiscount.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SellerMessage;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountDeliveryModeInfoService;
import com.smartErp.code.MainPage;
import com.smartErp.code.SystemInfo;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.enumerate.ReturnMessageEnum;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountApiManageController extends MainPage{
	
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	@Autowired
	private CdiscountDeliveryModeInfoService cdiscountDeliveryModeInfoService;
	
	@RequestMapping("cdiscountApiConfigManage")
	public String cdiscountApiConfigManage(Model model, HttpServletRequest request, Page page){
		String title = "navigator.cdiscount.api.config";
		_execute(page, request, model, title);
		List<Map<String, Object>> collection = cdiscountApiConfigService.getCdiscountApiConfigPage(page);
		Dumper.dump(collection);
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountApiConfigManage";
	}
	
	@RequestMapping("saveCdiscountApiConfig")
	@ResponseBody
	public String saveCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		Integer id = cdiscountApiConfig.getId();
		MyDate myDate = new MyDate();
		MyLocale myLocale = new MyLocale();
		cdiscountApiConfig.setLastUpdateTime(myDate.getCurrentDateTime());
		cdiscountApiConfig.setApiPassword(DESEncrypt.DataEncrypt(cdiscountApiConfig.getApiPassword()));
		String log = "";
		if (null != id) {
			log = myLocale.getText("update.cdiscount.api.config");
			CdiscountApiConfig cdiscountApiConfigOld = cdiscountApiConfigService.getCdiscountApiConfigById(id);
			cdiscountApiConfig.setSystemLog(SysRemark.appendMore(cdiscountApiConfigOld.getSystemLog(), log));
			cdiscountApiConfigService.updateCdiscountApiConfige(cdiscountApiConfig);
		} else {
			log = myLocale.getText("create.cdiscount.api.config");
			cdiscountApiConfig.setSystemLog(SysRemark.appendMore(cdiscountApiConfig.getSystemLog(), log));
			User user = UserSingleton.getInstance().getUser();
			cdiscountApiConfig.setCreator(user.getId());
			cdiscountApiConfig.setCreateDate(myDate.getCurrentDateTime());
			cdiscountApiConfigService.insertCdiscountApiConfig(cdiscountApiConfig);
		}
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("testConnectApi")
	@ResponseBody
	public String testConnectApi (String apiAccount, String apiPassword) {
		MyLocale myLocale = new MyLocale();
		String token = cdiscountApiConfigService.testConnectApi(apiAccount, apiPassword);
		ReturnMessage returnMessage = new ReturnMessage();
		if (StringUtils.isNotEmpty(token)) {
			if (token.contains("Cdiscount")) {
				returnMessage.setStatus(ReturnMessageEnum.FAIL.getValue());
				returnMessage.setMessage(myLocale.getText("account.or.password.error"));
			} else {
				returnMessage.setMessage(myLocale.getText("cdiscount.api.test.success"));
			}
		} else {
			returnMessage.setStatus(ReturnMessageEnum.WARRING.getValue());
			returnMessage.setMessage(myLocale.getText("internat.error.try.again"));
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("getCdiscountApiConfigById")
	@ResponseBody
	public String getCdiscountApiConfigById(Integer id) {
		CdiscountApiConfig cdiscountApiConfig = cdiscountApiConfigService.getCdiscountApiConfigById(id);
		return JsonUtil.toJsonStr(cdiscountApiConfig);
	}
	
	@RequestMapping("deleteCdiscountApiConfigById")
	@ResponseBody
	public String deleteCdiscountApiConfigById (Integer id) {
		cdiscountApiConfigService.deleteCdiscountApiConfigById(id);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("updateShopConfig")
	@ResponseBody
	public String updateShopConfig(Integer id) {
		SellerMessage sellerMessage = cdiscountApiConfigService.getSellerInfomation(id);
		if (null != sellerMessage) {
			cdiscountDeliveryModeInfoService.deleteDeliveryModeInfoByApiId(id);
			cdiscountDeliveryModeInfoService.saveDeliveryModeInfo(sellerMessage, id);
		}
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
}
