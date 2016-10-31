package com.smartErp.code.encryption;


/**
 * 加密解密工具类
 * 
 *
 */
public class DESEncrypt {

	/***
     * 加密密钥
     */    
    private  static String privateKey="6319dd2d032d503a92491398";
    
    
    /**
     * 加密方法提供3des加密，并且base64编码
     * @param key 24字节的密钥
     * @param str 明文
     * @return
     */
    public static String DataEncrypt(String str ) {
        String encrypt = null;
        try {
            byte[] ret = ThreeDes.encryptMode(privateKey.getBytes(),str.getBytes("UTF-8"));
            encrypt = new String(Base64Utils.encode(ret));
        } catch (Exception e) {
            System.out.print(e);
            encrypt = str;
        }
        return encrypt;
    }
    /***
     * 解密方法，先解密base64,在按3des解密
     * @param key 24字节的密钥
     * @param str 密文
     * @return
     */
    public static String DataDecrypt(String str ) {
        String decrypt = null;
        try {
            byte[] ret = ThreeDes.decryptMode(privateKey.getBytes(),Base64Utils.decode(str));
            decrypt = new String(ret, "UTF-8");
        } catch (Exception e) {
            System.out.print(e);
            decrypt = str;
        }
        return decrypt;
    }
    
    public static void main(String[] args) {
		String testString = "123";
		System.out.println(DESEncrypt.DataEncrypt(testString));
	}
}