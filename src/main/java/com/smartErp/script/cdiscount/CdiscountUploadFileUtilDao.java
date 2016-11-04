package com.smartErp.script.cdiscount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;

import com.smartErp.cdiscount.dao.CdiscountPublishImageDao;
import com.smartErp.cdiscount.dao.PublishDeliveryModeDao;
import com.smartErp.cdiscount.model.CdiscountPublish;
import com.smartErp.cdiscount.model.PublishDeliveryMode;
import com.smartErp.cdiscount.service.CdiscountUploadPackageService;
import com.smartErp.code.SystemInfo;
import com.smartErp.product.dao.ProductDao;
import com.smartErp.product.model.Product;
import com.smartErp.util.code.MyDate;
import com.smartErp.util.frame.SpringContextUtil;

public class CdiscountUploadFileUtilDao {
	private String scriptPath = SystemInfo.getScriptPath();
	private String packageZipPath = "/tmp/cdiscount/packageZipPath/";		//
	
	MyDate myDate = new MyDate();
	private CdiscountUploadPackageService service = new CdiscountUploadPackageService();
	
	public String packageCdiscountProduct(List<CdiscountPublish> cdiscountPublishList) {
		if (CollectionUtils.isEmpty(cdiscountPublishList)) {
			return null;
		}
		
		String currentDigitDateTime = myDate.getCurrentDigitDateTime();
		String productContentPath = packageZipPath + currentDigitDateTime + "/Content/";
		File contentPathFile = new File(productContentPath);
		if (!contentPathFile.exists() && !contentPathFile.isDirectory()) {
			contentPathFile.mkdirs();
		}

		String productXml = createProductXml(cdiscountPublishList);
		File productsFile = new File(productContentPath + "Products.xml");
		writeToFile(productsFile, productXml);
		String packagePath = packageZipPath + currentDigitDateTime + "/";
		String relsPah = packagePath + "_rels/.rels";
		String contentTypePath = packagePath + "[Content_Types].xml";
		copyFile(scriptPath + "cdiscount/submitProductPackageNecessaryFile/_rels/.rels", relsPah);
		copyFile(scriptPath + "cdiscount/submitProductPackageNecessaryFile/[Content_Types].xml", contentTypePath);
		try {
			String packageName = "product" + currentDigitDateTime + ".zip";
			CdiscountZipUtil.compress(packagePath, packagePath + packageName);
			File productPackageFile = new File(packagePath + packageName);
			String uploadPath = service.uploadProductPackage(productPackageFile);
			productPackageFile.delete();
			return uploadPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String createProductXml (List<CdiscountPublish> cdiscountPublishList) {
		String productxmlStr = "";
		if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
			ProductDao productDao = (ProductDao) SpringContextUtil.getBean("productDao");
			CdiscountPublishImageDao imageDao = (CdiscountPublishImageDao) SpringContextUtil.getBean("cdiscountPublishImageDao");
			String packageName = "int_pdt_seller_importiv" + new MyDate().getCurrentDigitDateTime();
			Integer listSize = cdiscountPublishList.size();
			String enter = "\n";
			productxmlStr = "<ProductPackage Name=\"" + packageName + "\" xmlns=\"clr-namespace:Cdiscount.Service.ProductIntegration.Pivot;assembly=Cdiscount.Service.ProductIntegration\" xmlns:x=\"http://schemas.microsoft.com/winfx/2006/xaml\">" + enter;
			productxmlStr += "<ProductPackage.Products>" + enter;
			productxmlStr += "<ProductCollection Capacity=\"" + listSize + "\">" + enter;
			for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
				productxmlStr += "<Product " + enter;
				productxmlStr += " BrandName = \"{brandName}\"" + enter;
				productxmlStr += " Description = \"{description}\"" + enter;
				productxmlStr += " Navigation = \"{navigation}\"" + enter;
				productxmlStr += " LongLabel = \"{longLabel}\"" + enter;
				productxmlStr += " ShortLabel = \"{shortLabel}\"" + enter;
				productxmlStr += " Model = \"SOUMISSION CREATION PRODUITS_MK\"" + enter;
				productxmlStr += " ProductKind = \"{productKind}\"" + enter;
				productxmlStr += " SellerProductId = \"{sku}\"" + enter;
				productxmlStr += " EncodedMarketingDescription = \"{marketingDescription}\">" + enter;
				
				productxmlStr += " <Product.EanList>" + enter;
				productxmlStr += "  <ProductEan Ean=\"{ean}\"/>" + enter;
				productxmlStr += " </Product.EanList>" + enter;
				
				productxmlStr += "<Product.Pictures>" + enter;
				productxmlStr += "{imageList}";
				productxmlStr += "</Product.Pictures>" + enter;
				
				productxmlStr += "</Product>" + enter;
				
				productxmlStr += "</ProductCollection>" + enter;
				productxmlStr += "</ProductPackage.Products>" + enter;
				productxmlStr += "</ProductPackage>" + enter;
				
				String brandName = cdiscountPublish.getBrandName();
				String navigation = cdiscountPublish.getNavigation();
				String description = cdiscountPublish.getDescription();
				String longLabel = cdiscountPublish.getLongLabel();
				String shortLabel = cdiscountPublish.getShortLabel();
				String productKind = cdiscountPublish.getProductKind();
				String ean = cdiscountPublish.getEan();
				Product product = productDao.getProductById(cdiscountPublish.getProductId());
				String sku = product.getSku();
				
				String marketingDescription  = cdiscountPublish.getMarketingDescription();
				byte[] marketingDescriptionByte = marketingDescription.getBytes();
				String marketingDescriptionBase64 = new String(Base64.encodeBase64(marketingDescriptionByte));
				System.out.println("marketingDescription length ===== > " + marketingDescriptionBase64.length());
				
				List<String> imageList = imageDao.getPublishImageList(cdiscountPublish.getId());
				String imageStr = "";
				if (CollectionUtils.isNotEmpty(imageList)) {
					for (String image : imageList) {
						imageStr += " <ProductImage Uri=\"" + image + "\"/>" + enter;
					}
				}
				
				productxmlStr = productxmlStr.replace("{brandName}", brandName);
				productxmlStr = productxmlStr.replace("{description}", description);
				productxmlStr = productxmlStr.replace("{navigation}", navigation);
				productxmlStr = productxmlStr.replace("{longLabel}", longLabel);
				productxmlStr = productxmlStr.replace("{shortLabel}", shortLabel);
				productxmlStr = productxmlStr.replace("{productKind}", productKind);
				productxmlStr = productxmlStr.replace("{sku}", sku);
				productxmlStr = productxmlStr.replace("{marketingDescription}", marketingDescriptionBase64);
				productxmlStr = productxmlStr.replace("{ean}", ean);
				productxmlStr = productxmlStr.replace("{imageList}", imageStr);
			}
		}
		System.out.println(productxmlStr);
		return productxmlStr;
	}
	
	public String packageCdiscountOffers(List<CdiscountPublish> cdiscountPublishList) {
		if (CollectionUtils.isEmpty(cdiscountPublishList)) {
			return null;
		}
		String currentDigitDateTime = myDate.getCurrentDigitDateTime();
		String offersContentPath = packageZipPath + currentDigitDateTime + "/Content/";
		File contentPathFile = new File(offersContentPath);
		if (!contentPathFile.exists() && !contentPathFile.isDirectory()) {
			contentPathFile.mkdirs();
		}

		String offersXml = createOffersXml(cdiscountPublishList);
		File offersFile = new File(offersContentPath + "Offers.xml");
		writeToFile(offersFile, offersXml);
		String packagePath = packageZipPath + currentDigitDateTime + "/";
		String relsPah = packagePath + "_rels/.rels";
		String contentTypePath = packagePath + "[Content_Types].xml";
		copyFile(scriptPath + "cdiscount/submitOfferPackageNecessaryFile/_rels/.rels", relsPah);
		copyFile(scriptPath + "cdiscount/submitOfferPackageNecessaryFile/[Content_Types].xml", contentTypePath);
		try {
			String packageName = "offers" + currentDigitDateTime + ".zip";
			CdiscountZipUtil.compress(packagePath, packagePath + packageName);
			File offersPackageFile = new File(packagePath + packageName);
			String uploadPath = service.uploadOffersPackage(offersPackageFile);
			offersPackageFile.delete();
			return uploadPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String createOffersXml(List<CdiscountPublish> cdiscountPublishList) {
		String productxmlStr = "";
		if (CollectionUtils.isNotEmpty(cdiscountPublishList)) {
			ProductDao productDao = (ProductDao) SpringContextUtil.getBean("productDao");
			PublishDeliveryModeDao publishDeliveryModeDao = (PublishDeliveryModeDao) SpringContextUtil.getBean("publishDeliveryModeDao");
			String packageName = "api_int_off_import_template_" + new MyDate().getCurrentDigitDateTime();
			Integer listSize = cdiscountPublishList.size();
			String enter = "\n";
			productxmlStr += "<OfferPackage Name=\"" + packageName + "\" PurgeAndReplace=\"False\" xmlns=\"clr-namespace:Cdiscount.Service.OfferIntegration.Pivot;assembly=Cdiscount.Service.OfferIntegration\" xmlns:x=\"http://schemas.microsoft.com/winfx/2006/xaml\">" + enter;
			productxmlStr += "	<OfferPackage.Offers>"  + enter;
			productxmlStr += "		<OfferCollection Capacity=\"" + listSize + "\">"  + enter;
			for (CdiscountPublish cdiscountPublish : cdiscountPublishList) {
				productxmlStr += "		<Offer "  + enter;
				productxmlStr += "			SellerProductId=\"{sku}\""  + enter;
				productxmlStr += "			ProductEan=\"{ean}\""  + enter;
				productxmlStr += "			ProductCondition=\"{productCondition}\""  + enter;
				productxmlStr += "			Price=\"{price}\""  + enter;
				productxmlStr += "			EcoPart=\"{ecoPart}\""  + enter;
				productxmlStr += "			DeaTax=\"{dea}\""  + enter;
				productxmlStr += "			Vat=\"{vat}\""  + enter;
				productxmlStr += "			Stock=\"{stockQty}\""  + enter;
				productxmlStr += "			PreparationTime=\"{preparationTime}\">"  + enter;
				productxmlStr += "			<Offer.ShippingInformationList>"  + enter;
				productxmlStr += "				<ShippingInformationList Capacity=\"{shippingInfoSize}\">"  + enter;
				productxmlStr += "{shippingInformationList}";
				productxmlStr += "				</ShippingInformationList>"  + enter;
				productxmlStr += "			</Offer.ShippingInformationList>"  + enter;
				productxmlStr += "		</Offer>"  + enter;
				
				Product product = productDao.getProductById(cdiscountPublish.getProductId());
				String sku = product.getSku();
				String ean = cdiscountPublish.getEan();
				String productCondition = cdiscountPublish.getProductCondition();
				String price = cdiscountPublish.getPrice() + "";
				String ecoPart = cdiscountPublish.getEcoPart() + "";
				String dea = cdiscountPublish.getDea() + "";
				String vat = cdiscountPublish.getVat() + "";
				String stockQty = cdiscountPublish.getStockQty() + "";
				String preparationTime = cdiscountPublish.getPreparationTime() + "";
				List<PublishDeliveryMode> publishDeliveryModeList = publishDeliveryModeDao.getPublishDeliveryModeListByPublishId(cdiscountPublish.getId());
				String shippingInfoSize = publishDeliveryModeList.size() + "";
				String shippingInformationListStr = "";
				for (PublishDeliveryMode publishDeliveryMode : publishDeliveryModeList) {
					shippingInformationListStr += "			<ShippingInformation AdditionalShippingCharges=\"" + publishDeliveryMode.getAddShippingCharges() + "\" DeliveryMode=\"" + publishDeliveryMode.getDeliveryMode() + "\" ShippingCharges=\"" + publishDeliveryMode.getShippingCharges() + "\" />" + enter;
				}
				productxmlStr = productxmlStr.replace("{sku}", sku);
				productxmlStr = productxmlStr.replace("{ean}", ean);
				productxmlStr = productxmlStr.replace("{productCondition}", productCondition);
				productxmlStr = productxmlStr.replace("{price}", price);
				productxmlStr = productxmlStr.replace("{ecoPart}", ecoPart);
				productxmlStr = productxmlStr.replace("{dea}", dea);
				productxmlStr = productxmlStr.replace("{vat}", vat);
				productxmlStr = productxmlStr.replace("{stockQty}", stockQty);
				productxmlStr = productxmlStr.replace("{preparationTime}", preparationTime);
				productxmlStr = productxmlStr.replace("{shippingInfoSize}", shippingInfoSize);
				productxmlStr = productxmlStr.replace("{shippingInformationList}", shippingInformationListStr);
			}
			productxmlStr += "		</OfferCollection>" + enter;
			productxmlStr += "	</OfferPackage.Offers>" + enter;
			productxmlStr += "	<OfferPackage.OfferPublicationList>" + enter;
			productxmlStr += "		<OfferPublicationList Capacity=\"1\">" + enter;
			productxmlStr += "			<PublicationPool Id=\"1\"/>" + enter;
			productxmlStr += "		</OfferPublicationList>" + enter;
			productxmlStr += "	</OfferPackage.OfferPublicationList>" + enter;
			productxmlStr += "</OfferPackage>" + enter;
		}
		System.out.println(productxmlStr);
		return productxmlStr;
	}
	
	
	private void writeToFile(File productsFile, String content) {
		try {
			FileOutputStream out = new FileOutputStream(productsFile);
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public boolean copyFile(String srcFileName, String destFileName) {
		File srcFile = new File(srcFileName);  
		if (!srcFile.exists()) { 
			System.out.println("src file " + srcFileName + " no exists!");
			return false;
		} else if (!srcFile.isFile()) {  
            System.out.println(srcFileName + " is not a file");
            return false;  
        }  
		
		File destFile = new File(destFileName);  
		if (!destFile.getParentFile().exists()) {  
			destFile.getParentFile().mkdirs();
		}
		
		int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
        
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
}