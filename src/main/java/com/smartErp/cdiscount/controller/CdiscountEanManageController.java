package com.smartErp.cdiscount.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.application.libraries.constentEnum.YesNoEnum;
import com.smartErp.cdiscount.model.CdiscountEan;
import com.smartErp.cdiscount.model.EanImportResult;
import com.smartErp.cdiscount.service.CdiscountEanService;
import com.smartErp.code.MainPage;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.frame.Page;

@Controller
@RequestMapping("cdiscount")
public class CdiscountEanManageController extends MainPage{
	
	@Autowired
	private CdiscountEanService cdiscountEanService;
	
	@RequestMapping("cdiscountEanManage")
	public String cdiscountEanManage(Model model, Page page){
		_execute(page, model);
		List<Map<String, Object>> collection = cdiscountEanService.getCdiscountEanManagePage(page);
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountEanManage";
	}
	
	@RequestMapping("batchSaveCdiscountEan")
	@ResponseBody
	public String batchSaveCdiscountEan (@RequestParam(value = "eanList[]", required = false) List<String> eanList) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer successCount = 0;
		Integer failCount = 0;
		List<EanImportResult> eanImportResultList = new ArrayList<EanImportResult>();
		if (CollectionUtils.isNotEmpty(eanList)) {
			User user = UserSingleton.getInstance().getUser(); 
			MyLocale myLocale = new MyLocale();
			String eanExist = myLocale.getText("cdiscount.ean.exist");
			String eanbadFormat = myLocale.getText("cdiscount.ean.bad.format");
			MyDate myDate = new MyDate();
			Pattern pattern = Pattern.compile("^[0-9]*$");
			for (String ean : eanList) {
				Matcher matcher = pattern.matcher(ean);
				boolean b = matcher.matches();
				if (!b || ean.length() > 20) {
					failCount++;
					EanImportResult eanImportResult = new EanImportResult();
					eanImportResult.setEan(ean);
					eanImportResult.setReason(eanbadFormat);
					eanImportResultList.add(eanImportResult);
				} else if (cdiscountEanService.checkEanExist(ean)) {
					failCount++;
					EanImportResult eanImportResult = new EanImportResult();
					eanImportResult.setEan(ean);
					eanImportResult.setReason(eanExist);
					eanImportResultList.add(eanImportResult);
				} else {
					successCount++;
					CdiscountEan cdiscountEan = new CdiscountEan();
					cdiscountEan.setCreateTime(myDate.getCurrentDateTime());
					cdiscountEan.setCreator(user.getId());
					cdiscountEan.setEan(ean);
					cdiscountEan.setIsUsed(0);
					cdiscountEanService.insert(cdiscountEan);
				}
			}
		}
		resultMap.put("successCount", successCount);
		resultMap.put("failCount", failCount);
		resultMap.put("eanImportResultList", eanImportResultList);
		return JsonUtil.toJsonStr(resultMap);
	}
	
	@RequestMapping("deleteCdiscountEan")
	@ResponseBody
	public String deleteCdiscountEan (Integer id) {
		ReturnMessage returnMessage = new ReturnMessage();
		cdiscountEanService.deleteById(id);
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("batchDeleteEan")
	@ResponseBody
	public String batchDeleteEan(@RequestParam(value = "idList[]", required = false) List<Integer> idList){
		ReturnMessage returnMessage = new ReturnMessage();
		if (CollectionUtils.isNotEmpty(idList)) {
			cdiscountEanService.deleteByIdList(idList);
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("getCdiscountEanCount")
	@ResponseBody
	public String getCdiscountEanCount() {
		User user = UserSingleton.getInstance().getUser();
		Integer count = cdiscountEanService.getCdiscountEanCount(user.getId(), YesNoEnum.NO.getValue());
		return JsonUtil.toJsonStr(count);
	}
	
	@RequestMapping("randomSelectEan")
	@ResponseBody
	public String randomSelectEan () {
		User user = UserSingleton.getInstance().getUser();
		List<CdiscountEan> cdiscountEanList = cdiscountEanService.getCdiscountEanList(user.getId(), YesNoEnum.NO.getValue());
		String ean = "";
		if (CollectionUtils.isNotEmpty(cdiscountEanList)) {
			ean = cdiscountEanList.get(0).getEan();
		}
		return JsonUtil.toJsonStr(ean);
	}
}
