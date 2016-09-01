package com.smartErp.cdiscount.util;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.ContextMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.Country;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.Currency;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.Language;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.LocalizationMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SecurityContext;
import com.smartErp.cdiscount.model.CdiscountApiConfig;

public class CdiscountHeaderMessageUtil {
	
	public static HeaderMessage getHeaderMessage(CdiscountApiConfig cdiscountApiConfig, String token) {
		ContextMessage context = new ContextMessage();
		context.setSiteID(100);
		context.setCustomerPoolID(1);
		context.setCatalogID(1);
		
		LocalizationMessage localization = new LocalizationMessage();
		localization.setCountry(Country.Fr);
		localization.setCurrency(Currency.Eur);
		localization.setDecimalPosition(2);
		localization.setLanguage(Language.Fr);
		
		SecurityContext security = new SecurityContext();
		security.setTokenId(token);
		security.setUserName(cdiscountApiConfig.getApiAccount());
		
		HeaderMessage headerMessage = new HeaderMessage();
		headerMessage.setContext(context);
		headerMessage.setLocalization(localization);
		headerMessage.setSecurity(security);
		headerMessage.setVersion("52.0");
		return headerMessage;
	}
}
