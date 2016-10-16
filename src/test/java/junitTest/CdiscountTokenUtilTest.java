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
		String name = "ElectronicHome-api";
		String password = "vyIRkN63eeM0nwaK0CWQPQ==";
		String encryptionPassword = DESEncrypt.DataDecrypt(password);
//		String encryptionPassword = DESEncrypt.DataEncrypt(password);
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setApiAccount(name);
		cdiscountApiConfig.setApiPassword(encryptionPassword);
		String token = CdiscountTokenUtil.getToken(cdiscountApiConfig);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
