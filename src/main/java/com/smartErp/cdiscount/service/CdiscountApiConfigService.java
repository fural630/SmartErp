package com.smartErp.cdiscount.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetSellerInformation;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetSellerInformationResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SellerMessage;
import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.frame.Page;

@Service
public class CdiscountApiConfigService {
	
	@Autowired(required=false)
	private CdiscountApiConfigDao cdiscountApiConfigDao;
	
	public void insertCdiscountApiConfig(CdiscountApiConfig cdiscountApiConfig) {
		cdiscountApiConfigDao.insert(cdiscountApiConfig);
	}
	
	public void updateCdiscountApiConfige(CdiscountApiConfig cdiscountApiConfig) {
		cdiscountApiConfigDao.update(cdiscountApiConfig);
	}
	
	public CdiscountApiConfig getCdiscountApiConfigById(Integer id) {
		return cdiscountApiConfigDao.getById(id);
	}
	
	public List<CdiscountApiConfig> getAllCdiscountApiConfig() {
		return cdiscountApiConfigDao.findAll();
	}
	
	public List<Map<String, Object>> getCdiscountApiConfigPage(Page page) {
		return cdiscountApiConfigDao.getCdiscountApiConfigPage(page);
	}
	
	public List<CdiscountApiConfig> getCdiscountApiConfigByCreator(Integer userId) {
		return cdiscountApiConfigDao.getCdiscountApiConfigByCreator(userId);
	}
	
	public void removeAll() {
		cdiscountApiConfigDao.removeAll();
	}

	public String testConnectApi(String apiAccount, String apiPassword) {
		String encryptionPassword = DESEncrypt.DataEncrypt(apiPassword);
		String token = CdiscountTokenUtil.getToken(apiAccount, encryptionPassword);
		return token;
	}

	public void deleteCdiscountApiConfigById(Integer id) {
		cdiscountApiConfigDao.deleteCdiscountApiConfigById(id);
	}

	public SellerMessage getSellerInfomation(CdiscountApiConfig cdiscountApiConfig) {
		if (null != cdiscountApiConfig) {
			try {
				GetSellerInformation paramGetSellerInformation = new GetSellerInformation();
				String token = CdiscountTokenUtil.getCashToken(cdiscountApiConfig);
				HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
				paramGetSellerInformation.setHeaderMessage(headerMessage);
				MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
				GetSellerInformationResponse response = marketplaceAPIServiceStub.getSellerInformation(paramGetSellerInformation);
				SellerMessage sellerMessage = response.getGetSellerInformationResult();
				return sellerMessage;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<CdiscountApiConfig> getApiConfigByCloseStatus(Integer status) {
		return cdiscountApiConfigDao.getApiConfigByCloseStatus(status);
	}
	
}
