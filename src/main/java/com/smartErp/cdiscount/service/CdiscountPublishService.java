package com.smartErp.cdiscount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartErp.application.libraries.constentEnum.CdiscountPublishStatusEnum;
import com.smartErp.cdiscount.dao.CdiscountPublishDao;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.code.session.UserSingleton;
import com.smartErp.system.model.User;
import com.smartErp.util.code.Dumper;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.code.MyLocale;
import com.smartErp.util.code.SysRemark;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountPublishService {
	
	@Autowired(required=false)
	CdiscountPublishDao cdiscountPublishDao;

	public void insertCdiscountPublish(CdiscountPublish cdiscountPublishForm) {
		MyLocale myLocale = new MyLocale();
		MyDate myDate = new MyDate();
		User user = UserSingleton.getInstance().getUser();
		String log = myLocale.getText("at.time.by.user.create.cdiscount.publish");
		cdiscountPublishForm.setLog(SysRemark.append("", log));
		cdiscountPublishForm.setCreator(user.getId());
		cdiscountPublishForm.setCreateTime(myDate.getCurrentDateTime());
		cdiscountPublishForm.setUpdateTime(myDate.getCurrentDateTime());
		cdiscountPublishForm.setPublishStatus(CdiscountPublishStatusEnum.WAIT_PENDING.getValue());
		cdiscountPublishDao.insertCdiscountPublish(cdiscountPublishForm);
	}

	public List<Map<String, Object>> getCdiscountPublishByPage(Page page) {
		return cdiscountPublishDao.getCdiscountPublishByPage(page);
	}
	
	public CdiscountPublish getCdiscountPublishById(Integer id) {
		return cdiscountPublishDao.getCdiscountPublishById(id);
	}

	public void updateCdiscountPublish(CdiscountPublish cdiscountPublishForm) {
		MyLocale myLocale = new MyLocale();
		MyDate myDate = new MyDate();
		String log = myLocale.getText("at.time.by.user.update.cdiscount.publish");
		Integer id = cdiscountPublishForm.getId();
		CdiscountPublish cdiscountPublish = cdiscountPublishDao.getCdiscountPublishById(id);
		cdiscountPublishForm.setLog(SysRemark.append(cdiscountPublish.getLog(), log));
		cdiscountPublishForm.setUpdateTime(myDate.getCurrentDateTime());
		cdiscountPublishForm.setPublishStatus(CdiscountPublishStatusEnum.WAIT_PENDING.getValue());
		cdiscountPublishDao.updateCdiscountPublish(cdiscountPublishForm);
	}

	public List<CdiscountPublish> getCdiscountPublishListByIdList(List<Integer> idList) {
		return cdiscountPublishDao.getCdiscountPublishListByIdList(idList);
	}

	public void batchUpdatePublishStatus(Integer publishStatus, List<Integer> idList) {
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("publishStatus", publishStatus);
		updateMap.put("idList", idList);
		cdiscountPublishDao.batchUpdatePublishStatus(updateMap);
	}

	public void deleteCdiscountPublishById(Integer id) {
		cdiscountPublishDao.deleteCdiscountPublishById(id);
	}
	
	
}
