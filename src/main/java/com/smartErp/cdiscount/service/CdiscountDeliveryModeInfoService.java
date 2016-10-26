package com.smartErp.cdiscount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ArrayOfDeliveryModeInformation;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.DeliveryModeInformation;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SellerMessage;
import com.smartErp.application.libraries.constentEnum.DeliveryModeCodeEnum;
import com.smartErp.application.libraries.constentEnum.DeliveryModeTypeEnum;
import com.smartErp.cdiscount.dao.CdiscountDeliveryModeInfoDao;
import com.smartErp.cdiscount.model.DeliveryModeInfor;
import com.smartErp.util.code.MyDate;

@Service
public class CdiscountDeliveryModeInfoService {
	
	@Autowired(required=false)
	CdiscountDeliveryModeInfoDao cdiscountDeliveryModeInfoDao;
	
	public List<DeliveryModeInfor> getDeliveryModeInfoByApiId(Integer apiId) {
		return cdiscountDeliveryModeInfoDao.getDeliveryModeInforListByApiId(apiId);
	}

	public void deleteDeliveryModeInfoByApiId(Integer apiId) {
		cdiscountDeliveryModeInfoDao.deleteDeliveryModeInforByApiId(apiId);
	}

	public void saveDeliveryModeInfo(SellerMessage sellerMessage, Integer apiId) {
		ArrayOfDeliveryModeInformation arrayOfDeliveryModeInformation = sellerMessage.getDeliveryModes();
		DeliveryModeInformation[] deliveryModeInformationList = arrayOfDeliveryModeInformation.getDeliveryModeInformation();
		if (null != deliveryModeInformationList && deliveryModeInformationList.length > 0) {
			MyDate myDate = new MyDate();
			for (int i = 0; i < deliveryModeInformationList.length; i++) {
				DeliveryModeInformation deliveryModeInformation = deliveryModeInformationList[i];
				String code = deliveryModeInformation.getCode();
				String modeNameFR = deliveryModeInformation.getName();
				Integer deliveryModeType = 0;
				if (code.equals(DeliveryModeCodeEnum.STD.name()) 
					|| code.equals(DeliveryModeCodeEnum.TRK.name())
					|| code.equals(DeliveryModeCodeEnum.REG.name())
					|| code.equals(DeliveryModeCodeEnum.EXP.name())) {
					deliveryModeType = DeliveryModeTypeEnum.SMALL_PARCEL.getValue();
				} else if (code.equals(DeliveryModeCodeEnum.LV1.name()) 
						|| code.equals(DeliveryModeCodeEnum.LV2.name())
						|| code.equals(DeliveryModeCodeEnum.LV3.name())) {
					deliveryModeType = DeliveryModeTypeEnum.BIG_PARCEL.getValue();
				}
				
				DeliveryModeInfor deliveryModeInfor = new DeliveryModeInfor();
				deliveryModeInfor.setApiId(apiId);
				deliveryModeInfor.setCode(code);
				deliveryModeInfor.setDeliveryModeType(deliveryModeType);
				deliveryModeInfor.setModeNameFR(modeNameFR);
				deliveryModeInfor.setUpdateTime(myDate.getCurrentDateTime());
				for (DeliveryModeCodeEnum modelCode : DeliveryModeCodeEnum.values()) {
					if (code.equals(modelCode.toString())) {
						deliveryModeInfor.setModeNameEN(modelCode.getValue());
					}
		    	}
				cdiscountDeliveryModeInfoDao.insertDeliveryModeInfor(deliveryModeInfor);
			}
		}
	}
}
