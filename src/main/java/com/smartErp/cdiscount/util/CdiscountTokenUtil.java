package com.smartErp.cdiscount.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.smartErp.cdiscount.dao.CdiscountApiConfigDao;
import com.smartErp.cdiscount.model.CdiscountApiConfig;
import com.smartErp.code.encryption.DESEncrypt;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.SpringContextUtil;

public class CdiscountTokenUtil {
	
	private static final String svcIssue = "https://sts.cdiscount.com/users/httpIssue.svc";
	private static final String svcToCall = "https://wsvc.cdiscount.com/MarketplaceAPIService.svc";
	
	public static String getToken(String apiAccount, String apiPassword) {
		CdiscountApiConfig cdiscountApiConfig = new CdiscountApiConfig();
		cdiscountApiConfig.setApiAccount(apiAccount);
		cdiscountApiConfig.setApiPassword(apiPassword);
		return getToken(cdiscountApiConfig);
	}
	
	public static String getToken(CdiscountApiConfig cdiscountApiConfig) {
		String token = "";
		for (int i = 0; i < 3; i++) {
			String name = cdiscountApiConfig.getApiAccount();
			String pwd = DESEncrypt.DataDecrypt(cdiscountApiConfig.getApiPassword());
			byte[] byteNamePwd = (name + ":" + pwd).getBytes();
			String afterBase64 = new String(Base64.encodeBase64(byteNamePwd));
			HttpClient httpClient = new HttpClient();
			HttpMethod method = new GetMethod(svcIssue + "/?realm=" + svcToCall);
			method.setRequestHeader("Authorization", "Basic " + afterBase64);
			method.setRequestHeader("Content-type", "application/xml");
			String resultXml = null;
			try {
				int response = httpClient.executeMethod(method);
				if (response == HttpStatus.SC_OK) {
					resultXml = method.getResponseBodyAsString();
					Document document = Jsoup.parse(resultXml);
					token = document.text();
					System.out.println("token ===> " + token);
					if (token.contains("Cdiscount")){
						token = "";		//密码错误
					}
					return token;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				method.releaseConnection();
			}
		}
		return token;
	}
	
	public static String getCashToken (CdiscountApiConfig cdiscountApiConfig) {
		String token = "";
		token = cdiscountApiConfig.getToken();
		String tokenTimeOut = cdiscountApiConfig.getTokenTimeOut();
		MyDate myDate = new MyDate();
		String currentTime = myDate.getCurrentTime();
		if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(tokenTimeOut)) {
			if (currentTime.compareTo(tokenTimeOut) > 0) {
				token = getToken(cdiscountApiConfig);
				if (StringUtils.isNotEmpty(token)) {
					CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
					cdiscountApiConfigDao.updateToken(token, myDate.getDateTime(1), cdiscountApiConfig.getId());
				}
			} else {
				return token;
			}
		} else {
			token = getToken(cdiscountApiConfig);
			if (StringUtils.isNotEmpty(token)) {
				CdiscountApiConfigDao cdiscountApiConfigDao = (CdiscountApiConfigDao) SpringContextUtil.getBean("cdiscountApiConfigDao");
				cdiscountApiConfigDao.updateToken(token, myDate.getDateTime(1), cdiscountApiConfig.getId());
			}
		}
		return token;
	}
}
