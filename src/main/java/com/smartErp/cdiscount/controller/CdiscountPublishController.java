package com.smartErp.cdiscount.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.model.CdiscountCategory;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.service.CdiscountApiConfigService;
import com.smartErp.cdiscount.service.CdiscountCategoryService;
import com.smartErp.cdiscount.service.CdiscountPublishService;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.product.model.Product;
import com.smartErp.product.service.ProductService;
import com.smartErp.system.model.ReturnMessage;
import com.smartErp.system.model.User;
import com.smartErp.util.code.Dumper;
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
			@RequestParam("sku") String sku) {
		
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
}
