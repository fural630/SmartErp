package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.cdiscount.dao.CdiscountEanDao;
import com.smartErp.cdiscount.model.CdiscountEan;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountEanService {
	
	@Autowired(required=false)
	private CdiscountEanDao cdiscountEanDao;

	public List<Map<String, Object>> getCdiscountEanManagePage(Page page) {
		return cdiscountEanDao.getCdiscountEanManagePage(page);
	}
	
	public boolean checkEanExist (String ean) {
		CdiscountEan cdiscountEan = cdiscountEanDao.getCdiscountEanByEan(ean);
		if (null == cdiscountEan) {
			return false;
		} 
		return true;
	}

	public void insert(CdiscountEan cdiscountEan) {
		cdiscountEanDao.insert(cdiscountEan);
	}

	public void deleteById(Integer id) {
		cdiscountEanDao.deleteById(id);
		
	}

	public void deleteByIdList(List<Integer> idList) {
		cdiscountEanDao.deleteByIdList(idList);
	}

	public Integer getCdiscountEanCount(Integer userId, Integer isUsed) {
		return cdiscountEanDao.getCdiscountEanCount(userId, isUsed);
	}
	
	public List<CdiscountEan> getCdiscountEanList(Integer userId, Integer isUsed) {
		return cdiscountEanDao.getCdiscountEanList(userId, isUsed);
	}
	
	public CdiscountEan getCdiscountEanByEanAndCreator(Integer userId, String ean) {
		return cdiscountEanDao.getCdiscountEanByEanAndCreator(userId, ean);
	}
	
	public void updateCdiscountEanIsUsed(Integer id, Integer isUsed) {
		cdiscountEanDao.updateCdiscountEanIsUsed(id, isUsed);
	}

	public void updateCdiscountEan(CdiscountEan cdiscountEan) {
		cdiscountEanDao.updateCdiscountEan(cdiscountEan);
		
	}

}
