package com.smartErp.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.code.session.UserSingleton;
import com.smartErp.product.dao.ProductDao;
import com.smartErp.product.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	public Product getProductBySku(String sku) {
		Integer creator = UserSingleton.getInstance().getUser().getId();
		return productDao.getProductBySkuAndCreator(sku, creator);
	}
	
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}
	
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	public void deleteProductImageByProductId(Integer productId) {
		productDao.deleteProductImageByProductId(productId);
	}
	
	public List<String> getImageListByProductId(Integer productId) {
		return productDao.getImageListByProductId(productId);
	}

	public void insertProductImage(Integer productId, String imageUrl) {
		productDao.insertProductImage(productId, imageUrl);
	}
	
	
}
