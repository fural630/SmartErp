package com.smartErp.cdiscount.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.model.DeliveryModeInfor;
import com.smartErp.cdiscount.model.PublishDeliveryMode;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountCategoryService;
import com.smartErp.cdiscount.service.CdiscountDeliveryModeInfoService;
import com.smartErp.cdiscount.service.CdiscountPublishService;
import com.smartErp.cdiscount.service.PublishDeliveryModeService;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.product.model.Product;
import com.smartErp.product.service.ProductService;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.util.code.JsonUtil;
import com.smartErp.util.code.MyDate;

@Controller
@RequestMapping("cdiscount")
public class CdiscountPublishController {
	
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
		MyDate myDate = new MyDate();
		User user = UserSingleton.getInstance().getUser();
		Product product = productService.getProductBySku(sku);
		if (product == null) {
			product = new Product();
			product.setCreator(user.getId());
			product.setSku(sku);
			product.setCreateTime(new MyDate().getCurrentDateTime());
			product.setUpdateTime(new MyDate().getCurrentDateTime());
			productService.insertProduct(product);
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
			cdiscountPublish.setCreator(user.getId());
			cdiscountPublish.setCreateTime(myDate.getCurrentDateTime());
			cdiscountPublish.setUpdateTime(myDate.getCurrentDateTime());
			cdiscountPublishService.insertCdiscountPublish(cdiscountPublish);
		} else {
//			cdiscountPublishService.updateCdiscountPublish(cdiscountPublish);
		}
		
		Integer publishId = cdiscountPublish.getId();
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
		ReturnMessage returnMessage = new ReturnMessage();
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
}
