package com.smartErp.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smartErp.product.model.Product;

public interface ProductDao {
	public Product getProductBySkuAndCreator(@Param("sku") String sku, @Param("creator") Integer creator);
	
	public void insertProduct(Product product);
	
	public void updateProduct(Product product);

	public void deleteProductImageByProductId(Integer productId);
	
	public List<String> getImageListByProductId(Integer productId);

	public void insertProductImage(@Param("productId") Integer productId, @Param("imageUrl")  String imageUrl);
}
