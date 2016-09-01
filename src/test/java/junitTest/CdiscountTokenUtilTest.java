package junitTest;

import static org.junit.Assert.*;

import org.apache.axis2.AxisFault;
import org.junit.Test;

import com.cdiscount.ws.stub.MarketplaceAPIServiceStub;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetSellerInformation;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.GetSellerInformationResponse;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.HeaderMessage;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.Seller;
import com.cdiscount.ws.stub.MarketplaceAPIServiceStub.SellerMessage;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.cdiscount.util.CdiscountHeaderMessageUtil;
import com.smartErp.cdiscount.util.CdiscountTokenUtil;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.code.Dumper;

public class CdiscountTokenUtilTest {

	@Test
	public void test() {
		try {
		String name = "SalesNEmma201623-api";
		String password = "9636";
		String encryptionPassword = DESEncrypt.DataEncrypt(password);
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setApiAccount(name);
		cdiscountApiConfig.setApiPassword(encryptionPassword);
		String token = CdiscountTokenUtil.getToken(cdiscountApiConfig);
		HeaderMessage headerMessage = CdiscountHeaderMessageUtil.getHeaderMessage(cdiscountApiConfig, token);
		GetSellerInformation getSellerInformationParam = new GetSellerInformation();
		getSellerInformationParam.setHeaderMessage(headerMessage);
		
		MarketplaceAPIServiceStub marketplaceAPIServiceStub;
		marketplaceAPIServiceStub = new MarketplaceAPIServiceStub();
		GetSellerInformationResponse response = marketplaceAPIServiceStub.getSellerInformation(getSellerInformationParam);
		SellerMessage message = response.getGetSellerInformationResult();
		Seller seller = message.getSeller();
		Dumper.dump(seller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
