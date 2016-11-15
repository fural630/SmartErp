package com.smartErp.cdiscount.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.smartErp.cdiscount.model.CdiscountEan;
import com.smartErp.util.frame.Page;

public interface CdiscountEanDao {
	public List<Map<String, Object>> getCdiscountEanManagePage(Page page);

	public CdiscountEan getCdiscountEanByEan(String ean);

	public void insert(CdiscountEan cdiscountEan);

	public void deleteById(Integer id);

	public void deleteByIdList(List<Integer> idList);

	public Integer getCdiscountEanCount(@Param("userId")Integer userId, @Param("isUsed")Integer isUsed);

	public List<CdiscountEan> getCdiscountEanList(@Param("userId")Integer userId, @Param("isUsed")Integer isUsed);

	public CdiscountEan getCdiscountEanByEanAndCreator(@Param("userId")Integer userId, @Param("ean") String ean);

	public void updateCdiscountEanIsUsed(@Param("id")Integer id, @Param("isUsed") Integer isUsed);

	public void updateCdiscountEan(CdiscountEan cdiscountEan);
}
