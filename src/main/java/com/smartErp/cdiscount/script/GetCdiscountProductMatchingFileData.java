package com.smartErp.cdiscount.script;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetProductPackageProductMatchingFileData;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetProductPackageProductMatchingFileDataResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.PackageFilter;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ProductMatchingFileMessage;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.util.code.Dumper;

public class GetCdiscountProductMatchingFileData {
	
	public static void main(String[] args) {
		String email = "ehome2016@kkmoon.com";
//		long packageId = 247846;
		long packageId = 250562;
//		long packageId = 250548;
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		String account = "ElectronicHome-api";
		String password = "Wakala@rich2016";
		cdiscountApiConfig.setApiAccount(account);
		cdiscountApiConfig.setApiPassword(password);
		try {
			MarketplaceAPIServiceStub marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
			GetProductPackageProductMatchingFileData getProductPackageProductMatchingFileData = new GetProductPackageProductMatchingFileData();
			String token = "f74465a236f84f60afeab969a8540cf0";
//			String token = CdiscountTokenUtil.getToken(cdiscountApiConfig);
			
			HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
			getProductPackageProductMatchingFileData.setHeaderMessage(headerMessage);
			PackageFilter packageFilter = new PackageFilter();
			packageFilter.setPackageID(packageId);
			getProductPackageProductMatchingFileData.setProductPackageFilter(packageFilter);
			GetProductPackageProductMatchingFileDataResponse response = marketplaceAPIServiceStub.getProductPackageProductMatchingFileData(getProductPackageProductMatchingFileData);
			ProductMatchingFileMessage message = response.getGetProductPackageProductMatchingFileDataResult();
			Dumper.dump(message);
			System.out.println(message.getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
