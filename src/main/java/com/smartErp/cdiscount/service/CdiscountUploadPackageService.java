package com.smartErp.cdiscount.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.lang.StringUtils;

public class CdiscountUploadPackageService {
	
	private static final String UPLOAD_PROCUT_PACKAGE = "http://198.11.207.246:8110/cdiscountUploadPackage/uploadProductPackage";
	private static final String UPLOAD_OFFERS_PACKAGE = "http://198.11.207.246:8110/cdiscountUploadPackage/uploadOfferPackage";
	
//	private static final String UPLOAD_PROCUT_PACKAGE = "http://192.168.220.45:8228/cdiscountUploadPackage/uploadProductPackage";
//	private static final String UPLOAD_OFFERS_PACKAGE = "http://192.168.220.45:8228/cdiscountUploadPackage/uploadOfferPackage";
	
	public String uploadProductPackage(File uploadFile) {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(UPLOAD_PROCUT_PACKAGE);
		try {
			FilePart fp = new FilePart("filedata", uploadFile);
			fp.setContentType(uploadFile.getAbsolutePath());
			System.out.println("uploadFileAbsolutepath = " + uploadFile.getAbsolutePath());
			Part[] parts = { fp };  
			MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, postMethod.getParams());  
			postMethod.setRequestEntity(multipartRequestEntity);  
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int statusCode = httpClient.executeMethod(postMethod);
			System.out.println("statusCode = " + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				String resultPath = postMethod.getResponseBodyAsString();
				if (StringUtils.isNotEmpty(resultPath)) {
					System.out.println("resultPath = " + resultPath);
					return resultPath;
				} else {
					return null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			 postMethod.releaseConnection(); 
		}
		return null;
	}
	
	public String uploadOffersPackage(File uploadFile) { 
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(UPLOAD_OFFERS_PACKAGE);
		try {
			FilePart fp = new FilePart("filedata", uploadFile);
			fp.setContentType(uploadFile.getAbsolutePath());
			System.out.println("uploadFileAbsolutepath = " + uploadFile.getAbsolutePath());
			Part[] parts = { fp };  
			MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, postMethod.getParams());  
			postMethod.setRequestEntity(multipartRequestEntity);  
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int statusCode = httpClient.executeMethod(postMethod);
			System.out.println("statusCode = " + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				String resultPath = postMethod.getResponseBodyAsString();
				if (StringUtils.isNotEmpty(resultPath)) {
					System.out.println("resultPath = " + resultPath);
					return resultPath;
				} else {
					return null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			 postMethod.releaseConnection(); 
		}
		return null;
	}
	
	public static void main(String[] args) {
		CdiscountUploadPackageService service = new CdiscountUploadPackageService();
		File file = new File("/home/tomtop2028/productPackageTemplate/160729180418/product160729180418.zip");
		String result = service.uploadProductPackage(file);
		System.out.println(result);
	}
}
