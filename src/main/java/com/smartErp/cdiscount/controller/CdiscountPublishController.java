package com.smartErp.cdiscount.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.model.CdiscountPublishImage;
import com.smartErp.cdiscount.model.DeliveryModeInfor;
import com.smartErp.cdiscount.model.PublishDeliveryMode;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountCategoryService;
import com.smartErp.cdiscount.service.CdiscountDeliveryModeInfoService;
import com.smartErp.cdiscount.service.CdiscountPublishImageService;
import com.smartErp.cdiscount.service.CdiscountPublishService;
import com.smartErp.cdiscount.service.PublishDeliveryModeService;
import com.smartErp.code.MainPage;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.product.model.Product;
import com.smartErp.product.service.ProductService;
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
public class CdiscountPublishController extends MainPage{
	
	@Autowired
	private CdiscountApiConfigService cdiscountApiConfigService;
	@Autowired
	private CdiscountCategoryService cdiscountCategoryService;
	@Autowired
	private CdiscountPublishService cdiscountPublishService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CdiscountDeliveryModeInfoService cdiscountDeliveryModeInfoService;
	@Autowired
	private PublishDeliveryModeService publishDeliveryModeService;
	@Autowired
	private CdiscountPublishImageService cdiscountPublishImageService;
	
	@RequestMapping("cdiscountPublishManage")
	public String cdiscountPublishManage(Model model, Page page){
		String title = "navigator.cdiscount.publish.manage";
		_execute(page, model, title);
		List<Map<String, Object>> collection = cdiscountPublishService.getCdiscountPublishByPage(page);
		model.addAttribute("collection", collection);
		return "cdiscount/cdiscountPublishManage";
	}
	
	
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
		List<CdiscountCategory> firstCategoryList = cdiscountCategoryService.getFirstCategory();
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
	
	@RequestMapping("saveCdiscountPublish")
	@ResponseBody
	public String saveCdiscountPublish(CdiscountPublish cdiscountPublish,
			@RequestParam(value = "selectedImageList[]", required = false) List<String> selectedImageList,
			@RequestParam(value = "allImageList[]", required = false) List<String> allImageList,
			@RequestParam("sku") String sku, @RequestParam("publishDeliveryModeList") String publishDeliveryModeStr) {
		ReturnMessage returnMessage = new ReturnMessage();
		MyLocale myLocale = new MyLocale();
		if (null != cdiscountPublish.getId()) {
			CdiscountPublish cdiscountPublishOld = cdiscountPublishService.getCdiscountPublishById(cdiscountPublish.getId());
			Integer publishStatus = cdiscountPublishOld.getPublishStatus();
			if (!publishStatus.equals(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue())
					&& !publishStatus.equals(CdiscountPublishStatusEnum.WAIT_PENDING.getValue())) {
				returnMessage.setStatus(ReturnMessageEnum.WARRING.getValue());
				returnMessage.setMessage(myLocale.getText("publish.ing.can.not.modify.data"));
				return JsonUtil.toJsonStr(returnMessage);
			}
		}
		Product product = productService.getProductBySku(sku);
		if (product == null) {
			productService.insertProduct(sku);
		} 
		Integer productId = product.getId();
		if (CollectionUtils.isNotEmpty(allImageList)) {
			productService.deleteProductImageByProductId(productId);
			for (String imageUrl : allImageList) {
				productService.insertProductImage(productId, imageUrl);
			}
		}
		
		cdiscountPublish.setProductId(productId);
		if (cdiscountPublish.getId() == null) {
			cdiscountPublishService.insertCdiscountPublish(cdiscountPublish);
		} else {
			cdiscountPublishService.updateCdiscountPublishForm(cdiscountPublish);
		}
		
		Integer publishId = cdiscountPublish.getId();
		if (CollectionUtils.isNotEmpty(selectedImageList)) {
			cdiscountPublishImageService.deletePublishImageByPublishId(publishId);
			for (String imageUrl : selectedImageList) {
				cdiscountPublishImageService.insertPublishImage(imageUrl, publishId);
			}
		}
		
		Gson gson = new Gson();
		List publishDeliveryModeList = gson.fromJson(publishDeliveryModeStr, List.class);
		if (CollectionUtils.isNotEmpty(publishDeliveryModeList)) {
			publishDeliveryModeService.deletePublishDeliveryModeByPublishId(publishId);
			for (Object object : publishDeliveryModeList) {
				PublishDeliveryMode publishDeliveryMode = (PublishDeliveryMode) gson.fromJson(gson.toJson(object), PublishDeliveryMode.class);
				publishDeliveryMode.setPublishId(publishId);
				publishDeliveryModeService.insertPublishDeliveryMode(publishDeliveryMode);
			}
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("reloadSkuImage")
	@ResponseBody
	public String reloadSkuImage(String sku) {
		List<String> allImageList = new ArrayList<String>();
		Product product = productService.getProductBySku(sku);
		if (null != product) {
			Integer productId = product.getId();
			allImageList = productService.getImageListByProductId(productId);
		}
		return JsonUtil.toJsonStr(allImageList);
	}
	
	@RequestMapping("getDeliveryModeInfoByApiId")
	@ResponseBody
	public String getDeliveryModeInfoByApiId(Integer apiId) {
		List<DeliveryModeInfor> deliveryModeInforList = cdiscountDeliveryModeInfoService.getDeliveryModeInfoByApiId(apiId);
		return JsonUtil.toJsonStr(deliveryModeInforList);
	}
	
	@RequestMapping("editCdiscountPublish")
	@ResponseBody
	public String editCdiscountPublish(Integer publishId) {
		CdiscountPublish cdiscountPublish = cdiscountPublishService.getCdiscountPublishById(publishId);
		Map<String, Object> resultDataMap = new HashMap<String, Object>();
		if (null != cdiscountPublish) {
			List<String> publishImageList = cdiscountPublishImageService.getPublishImageList(publishId);
			List<PublishDeliveryMode> publishDeliveryModeList = publishDeliveryModeService.getPublishDeliveryModeListByPublishId(publishId);
			Integer productId = cdiscountPublish.getProductId();
			String sku = productService.getSkuByProductId(productId);
			resultDataMap.put("publishImageList", publishImageList);
			resultDataMap.put("publishDeliveryModeList", publishDeliveryModeList);
			resultDataMap.put("sku", sku);
			resultDataMap.put("cdiscountPublish", cdiscountPublish);
		}
		return JsonUtil.toJsonStr(resultDataMap);
	} 
	
	@RequestMapping("getCategoryPathByCategoryCode")
	@ResponseBody
	public String getCategoryPathByCategoryCode(String categoryCode) {
		MyLocale myLocale = new MyLocale();
		Map<String, Object> categoryInfoMap = new HashMap<String, Object>();
		CdiscountCategory cdiscountCategory = cdiscountCategoryService.getCdiscountCategoryByCode(categoryCode);
		if (null == cdiscountCategory) {
			String errorMassage = myLocale.getText("this.category.code.is.not.exist", categoryCode);
			categoryInfoMap.put("errorMassage", errorMassage);
		} else {
			categoryInfoMap = cdiscountCategoryService.getCdiscountPublishCategoryInfo(cdiscountCategory);
		}
		return JsonUtil.toJsonStr(categoryInfoMap);
	}
	
	
	@RequestMapping("batchShelvesProduct")
	@ResponseBody
	public String batchShelvesProduct(@RequestParam(value = "idList[]", required = false) List<Integer> idList) {
		ReturnMessage returnMessage = new ReturnMessage();
		if (CollectionUtils.isNotEmpty(idList)) {
			MyLocale myLocale = new MyLocale();
			String message = "";
			List<CdiscountPublish> cdiscountPublishList = cdiscountPublishService.getCdiscountPublishListByIdList(idList);
			if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
				Set<Integer> apiSet = new HashSet<Integer>();
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					Integer publishStatus = cdiscountPublish.getPublishStatus();
					if (!publishStatus.equals(CdiscountPublishStatusEnum.WAIT_PENDING.getValue())) {
						message = myLocale.getText("publish.status.mast.be.the.id.is.not", String.valueOf(cdiscountPublish.getId()), myLocale.getText(CdiscountPublishStatusEnum.WAIT_PENDING.toString()));
						returnMessage.setMessage(message);
						returnMessage.setStatus(0);
						return JsonUtil.toJsonStr(returnMessage);
					}
					Integer apiId = cdiscountPublish.getApiId();
					apiSet.add(apiId);
				}
				if (apiSet.size() > 1) {
					message = myLocale.getText("upload.cdiscount.package.can.not.repeat.store");
					returnMessage.setMessage(message);
					returnMessage.setStatus(0);
					return JsonUtil.toJsonStr(returnMessage);
				}
				String newLog = myLocale.getText("at.time.by.user.batch.update.publish.status", myLocale.getText(CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_BASIC_INFO.toString()));
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_BASIC_INFO.getValue());
					cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), newLog));
					cdiscountPublish.setProductPackageId(null);
					cdiscountPublishService.updateCdiscountPublish(cdiscountPublish);
				}
			}
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("batchUploadOffers")
	@ResponseBody
	public String batchUploadOffers(@RequestParam(value = "idList[]", required = false) List<Integer> idList) {
		ReturnMessage returnMessage = new ReturnMessage();
		if (CollectionUtils.isNotEmpty(idList)) {
			MyLocale myLocale = new MyLocale();
			String message = "";
			List<CdiscountPublish> cdiscountPublishList = cdiscountPublishService.getCdiscountPublishListByIdList(idList);
			if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
				Set<Integer> apiSet = new HashSet<Integer>();
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					Integer publishStatus = cdiscountPublish.getPublishStatus();
					if (!publishStatus.equals(CdiscountPublishStatusEnum.PLATFORM_MANUAL_VALIDATE_ING.getValue())) {
						message = myLocale.getText("publish.status.mast.be.the.id.is.not", String.valueOf(cdiscountPublish.getId()), myLocale.getText(CdiscountPublishStatusEnum.PLATFORM_MANUAL_VALIDATE_ING.toString()));
						returnMessage.setMessage(message);
						returnMessage.setStatus(0);
						return JsonUtil.toJsonStr(returnMessage);
					}
					Integer apiId = cdiscountPublish.getApiId();
					apiSet.add(apiId);
				}
				if (apiSet.size() > 1) {
					message = myLocale.getText("upload.cdiscount.package.can.not.repeat.store");
					returnMessage.setMessage(message);
					returnMessage.setStatus(0);
					return JsonUtil.toJsonStr(returnMessage);
				}
				String newLog = myLocale.getText("at.time.by.user.batch.update.publish.status", myLocale.getText(CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_OFFERS.toString()));
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.WAIT_SYSTEM_UPLOAD_OFFERS.getValue());
					cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), newLog));
					cdiscountPublishService.updateCdiscountPublish(cdiscountPublish);
				}
			}
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	@RequestMapping("batchUpdateToWaitPendding")
	@ResponseBody
	public String batchUpdateToWaitPendding (@RequestParam(value = "idList[]", required = false) List<Integer> idList) {
		ReturnMessage returnMessage = new ReturnMessage();
		if (CollectionUtils.isNotEmpty(idList)) {
			MyLocale myLocale = new MyLocale();
			String message = "";
			List<CdiscountPublish> cdiscountPublishList = cdiscountPublishService.getCdiscountPublishListByIdList(idList);
			if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
				Set<Integer> apiSet = new HashSet<Integer>();
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					Integer publishStatus = cdiscountPublish.getPublishStatus();
					if (!publishStatus.equals(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue())) {
						message = myLocale.getText("publish.status.mast.be.the.id.is.not", String.valueOf(cdiscountPublish.getId()), myLocale.getText(CdiscountPublishStatusEnum.PUBLISHED_FAIL.toString()));
						returnMessage.setMessage(message);
						returnMessage.setStatus(0);
						return JsonUtil.toJsonStr(returnMessage);
					}
					Integer apiId = cdiscountPublish.getApiId();
					apiSet.add(apiId);
				}
				if (apiSet.size() > 1) {
					message = myLocale.getText("upload.cdiscount.package.can.not.repeat.store");
					returnMessage.setMessage(message);
					returnMessage.setStatus(0);
					return JsonUtil.toJsonStr(returnMessage);
				}
				String newLog = myLocale.getText("at.time.by.user.batch.update.publish.status", myLocale.getText(CdiscountPublishStatusEnum.WAIT_PENDING.toString()));
				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
					cdiscountPublish.setPublishStatus(CdiscountPublishStatusEnum.WAIT_PENDING.getValue());
					cdiscountPublish.setLog(SysRemark.append(cdiscountPublish.getLog(), newLog));
					cdiscountPublishService.updateCdiscountPublish(cdiscountPublish);
				}
			}
		}
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	
	@RequestMapping("deleteCdiscountPublishById")
	@ResponseBody
	public String deleteCdiscountPublishById (Integer id) {
		cdiscountPublishService.deleteCdiscountPublishById(id);
		ReturnMessage returnMessage = new ReturnMessage();
		return JsonUtil.toJsonStr(returnMessage);
	}
	
	
//	@RequestMapping("batchUpdateToWaitPendding")
//	@ResponseBody
//	public String batchDeleteCdiscountPublish (@RequestParam(value = "idList[]", required = false) List<Integer> idList) {
//		ReturnMessage returnMessage = new ReturnMessage();
//		if (CollectionUtils.isNotEmpty(idList)) {
//			MyLocale myLocale = new MyLocale();
//			String message = "";
//			List<CdiscountPublish> cdiscountPublishList = cdiscountPublishService.getCdiscountPublishListByIdList(idList);
//			if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
//				Set<Integer> apiSet = new HashSet<Integer>();
//				for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
//					Integer publishStatus = cdiscountPublish.getPublishStatus();
//					if (!publishStatus.equals(CdiscountPublishStatusEnum.PUBLISHED_FAIL.getValue())) {
//						message = myLocale.getText("publish.status.mast.be.the.id.is.not", String.valueOf(cdiscountPublish.getId()), myLocale.getText(CdiscountPublishStatusEnum.PUBLISHED_FAIL.toString()));
//						returnMessage.setMessage(message);
//						returnMessage.setStatus(0);
//						return JsonUtil.toJsonStr(returnMessage);
//					}
//					Integer apiId = cdiscountPublish.getApiId();
//					apiSet.add(apiId);
//				}
//				if (apiSet.size() > 1) {
//					message = myLocale.getText("upload.cdiscount.package.can.not.repeat.store");
//					returnMessage.setMessage(message);
//					returnMessage.setStatus(0);
//					return JsonUtil.toJsonStr(returnMessage);
//				}
//				cdiscountPublishService.batchUpdatePublishStatus(CdiscountPublishStatusEnum.WAIT_PENDING.getValue(), idList);
//			}
//		}
//		return JsonUtil.toJsonStr(returnMessage);
//	}
}
