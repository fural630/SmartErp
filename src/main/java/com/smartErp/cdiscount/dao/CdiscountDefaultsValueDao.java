package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.smartErp.cdiscount.model.CdiscountDefaultsValue;
import com.smartErp.util.frame.Page;

public interface CdiscountDefaultsValueDao {

	public List<CdiscountDefaultsValue> getDefaultsTemplate(@Param("userId") Integer userId, @Param("isDefaults") Integer isDefaults);

	public void insertCdiscountDefaultsValue(CdiscountDefaultsValue cdiscountDefaultsValue);
	
	public List<Map<String, Object>> getCdiscountDefaultsValueByPage(Page page);

	public void deleteById(Integer id);
	
	public CdiscountDefaultsValue getCdiscountDefaultsValueById(Integer id);

	public void udpateCdiscountDefaultsValue(CdiscountDefaultsValue cdiscountDefaultsValue);

	public void updateIsDefaultsTemplate(@Param("isDefaults")Integer isDefaults,@Param("userId") Integer userId);

	public void setAsDefaultsTemplate(@Param("id")Integer id, @Param("isDefaults")Integer isDefaults);

	public List<CdiscountDefaultsValue> getCdiscountDefaultsValueByCreator(Integer userId);

	public CdiscountDefaultsValue getDefaultsTemplateValue(@Param("userId")Integer userId, @Param("isDefaults")Integer isDefaults);
	
}
