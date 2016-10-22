//package com.smartErp.script.cdiscount;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import org.apache.commons.collections.CollectionUtils;
//
//import com.smartErp.cdiscount.model.CdiscountPublish;
//import com.smartErp.util.code.MyDate;
//
//public class CdiscountUtilDao {
////	private String scriptPath = SystemInfo.getScriptPath();
//	private String scriptPath = "/home/tomtop2028/workspace/tomtoperp/src/main/resources/com/tomtop/script/";
//	private String productTmplatePath = scriptPath + "cdiscount/publishProduct/cdiscount-product-template.xml";
//	private String offersTmplatePath = scriptPath + "cdiscount/publishProduct/cdiscount-offer-template.xml";
//	private String packageZipPath = "/tmp/cdiscount/packageZipPath/";		//
//	
//	MyDate myDate = new MyDate();
////	private CdiscountUploadPackageService service = new CdiscountUploadPackageService();
//	
//	
//	public String packageCdiscountOffers(List<CdiscountPublish> cdiscountPublishList) {
//		if (CollectionUtils.isEmpty(cdiscountPublishList)) {
//			return null;
//		}
//		String currentDigitDateTime = myDate.getCurrentDigitDateTime();
//		String offersContentPath = packageZipPath + currentDigitDateTime + "/Content/";
//		File contentPathFile = new File(offersContentPath);
//		if (!contentPathFile.exists() && !contentPathFile.isDirectory()) {
//			contentPathFile.mkdirs();
//		}
//
//		String offersXml = createOffersXml(cdiscountPublishList);
//		File offersFile = new File(offersContentPath + "Offers.xml");
//		writeToFile(offersFile, offersXml);
//		String packagePath = packageZipPath + currentDigitDateTime + "/";
//		String relsPah = packagePath + "_rels/.rels";
//		String contentTypePath = packagePath + "[Content_Types].xml";
//		copyFile(scriptPath + "cdiscount/publishProduct/submitOfferPackageNecessaryFile/_rels/.rels", relsPah);
//		copyFile(scriptPath + "cdiscount/publishProduct/submitOfferPackageNecessaryFile/[Content_Types].xml", contentTypePath);
//		try {
//			String packageName = "offers" + currentDigitDateTime + ".zip";
//			CdiscountZipUtil.compress(packagePath, packagePath + packageName);
//			File offersPackageFile = new File(packagePath + packageName);
////			return service.uploadOffersPackage(offersPackageFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
//	
//	public String packageCdiscountProduct(List<CdiscountPublish> cdiscountPublishList) {
//		if (CollectionUtils.isEmpty(cdiscountPublishList)) {
//			return null;
//		}
//		
//		String currentDigitDateTime = myDate.getCurrentDigitDateTime();
//		String productContentPath = packageZipPath + currentDigitDateTime + "/Content/";
//		File contentPathFile = new File(productContentPath);
//		if (!contentPathFile.exists() && !contentPathFile.isDirectory()) {
//			contentPathFile.mkdirs();
//		}
//
//		String productXml = createProductXml(cdiscountPublishList);
//		File productsFile = new File(productContentPath + "Products.xml");
//		writeToFile(productsFile, productXml);
//		String packagePath = packageZipPath + currentDigitDateTime + "/";
//		String relsPah = packagePath + "_rels/.rels";
//		String contentTypePath = packagePath + "[Content_Types].xml";
//		copyFile(scriptPath + "cdiscount/publishProduct/submitProductPackageNecessaryFile/_rels/.rels", relsPah);
//		copyFile(scriptPath + "cdiscount/publishProduct/submitProductPackageNecessaryFile/[Content_Types].xml", contentTypePath);
//		try {
//			String packageName = "product" + currentDigitDateTime + ".zip";
//			CdiscountZipUtil.compress(packagePath, packagePath + packageName);
//			File productPackageFile = new File(packagePath + packageName);
//			return service.uploadProductPackage(productPackageFile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	private void writeToFile(File productsFile, String content) {
//		try {
//			FileOutputStream out = new FileOutputStream(productsFile);
//			out.write(content.getBytes("utf-8"));
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}
//	
//	private String createProductXml (List<CdiscountPublish> cdiscountPublishList) {
//		Template template = new Template(productTmplatePath);
//		HashSet<HashMap<String, String>> moreArgsSet = new HashSet<HashMap<String, String>>();
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("packageName", "int_pdt_seller_importiv" + new MyDate().getCurrentDigitDateTime());
//		if (CollectionUtils.isNotEmpty(backstagePublishListingList)) {
//			Gson gson = new Gson();
//			for (CdiscountBackstagePublishListing cdiscountBackstagePublishListing : backstagePublishListingList) {
//				HashMap<String, String> moreArgs = new HashMap<String, String>();
//				Map<String, Object> publishParamMap = gson.fromJson(gson.toJson(cdiscountBackstagePublishListing), Map.class);
//				List<String> imageList = (List<String>) publishParamMap.get("imageList");
//				String imageListStr = "";
//				if (CollectionUtils.isNotEmpty(imageList)) {
//					for (String url : imageList) {
//						imageListStr += "<ProductImage Uri=\"" + url + "\"/>";
//					}
//				}
//				if (StringUtils.isNotEmpty(imageListStr)) {
//					moreArgs.put("imageList", imageListStr);
//				}
//				publishParamMap.remove("imageList");
//				
//				for (String key : publishParamMap.keySet()) {
//					Object value = publishParamMap.get(key);
//					if ("marketingDescription".equals(key)) {
//						String marketingDescription = String.valueOf(value);
//						byte[] marketingDescriptionByte = marketingDescription.getBytes();
//						String marketingDescriptionBase64 = new String(Base64.encodeBase64(marketingDescriptionByte));
//						System.out.println("marketingDescription length ===== > " + marketingDescriptionBase64.length());
//						moreArgs.put("marketingDescription", marketingDescriptionBase64);
//					} else {
//						moreArgs.put(key, String.valueOf(value));
//					}
//				}
//				moreArgsSet.add(moreArgs);
//			}
//			paramMap.put("productMapList", moreArgsSet);
//			paramMap.put("capacity", String.valueOf(backstagePublishListingList.size()));
//		}
//		template.setArgs(paramMap);
//		String templateStr = template.parse();
//		System.out.println(templateStr);
//		return templateStr;
//	}
//	
//	private String createOffersXml(List<CdiscountPublish> cdiscountPublishList) {
//		Template template = new Template(offersTmplatePath);
//		HashSet<HashMap<String, String>> moreArgsSet = new HashSet<HashMap<String, String>>();
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("packageName", "api_int_off_import_template_" + new MyDate().getCurrentDigitDateTime());
//		if (CollectionUtils.isNotEmpty(publishListings)) {
//			for (CdiscountBackstagePublishListing publishListing : publishListings) {
//				HashMap<String, String> moreArgs = new HashMap<String, String>();
//				moreArgs.put("sku", publishListing.getSku());
//				moreArgs.put("ean", publishListing.getEan());
//				moreArgs.put("productCondition", publishListing.getProductCondition());
//				moreArgs.put("price", publishListing.getPrice() + "");
//				moreArgs.put("ecoPart", publishListing.getEcoPart() + "");
//				moreArgs.put("dea", publishListing.getDea() + "");
//				moreArgs.put("vat", publishListing.getVat() + "");
//				moreArgs.put("stockQty", publishListing.getStockQty() + "");
//				moreArgs.put("preparationTime", publishListing.getPreparationTime() + "");
//				moreArgs.put("standardAdditionalShippingCharges", publishListing.getStandardAdditionalShippingCharges() + "");
//				moreArgs.put("trackedAdditionalShippingCharges", publishListing.getTrackedAdditionalShippingCharges() + "");
//				moreArgs.put("registeredAdditionalShippingCharges", publishListing.getRegisteredAdditionalShippingCharges() + "");
//				moreArgs.put("standardShippingCharges", publishListing.getStandardShippingCharges() + "");
//				moreArgs.put("trackedShippingCharges", publishListing.getTrackedShippingCharges() + "");
//				moreArgs.put("registeredShippingCharges", publishListing.getRegisteredShippingCharges() + "");
//				moreArgsSet.add(moreArgs);
//			}
//			paramMap.put("offerList", moreArgsSet);
//			paramMap.put("capacity", String.valueOf(publishListings.size()));
//		}
//		template.setArgs(paramMap);
//		String templateStr = template.parse();
//		System.out.println(templateStr);
//		return templateStr;
//	}
//	
//	public boolean copyFile(String srcFileName, String destFileName) {
//		File srcFile = new File(srcFileName);  
//		if (!srcFile.exists()) { 
//			System.out.println("src file " + srcFileName + " no exists!");
//			return false;
//		} else if (!srcFile.isFile()) {  
//            System.out.println(srcFileName + " is not a file");
//            return false;  
//        }  
//		
//		File destFile = new File(destFileName);  
//		if (!destFile.getParentFile().exists()) {  
//			destFile.getParentFile().mkdirs();
//		}
//		
//		int byteread = 0; // 读取的字节数  
//        InputStream in = null;  
//        OutputStream out = null;  
//        
//        try {  
//            in = new FileInputStream(srcFile);  
//            out = new FileOutputStream(destFile);  
//            byte[] buffer = new byte[1024];  
//  
//            while ((byteread = in.read(buffer)) != -1) {  
//                out.write(buffer, 0, byteread);  
//            }  
//            return true;  
//        } catch (FileNotFoundException e) {  
//            return false;  
//        } catch (IOException e) {  
//            return false;  
//        } finally {  
//            try {  
//                if (out != null)  
//                    out.close();  
//                if (in != null)  
//                    in.close();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }  
//	}
//}