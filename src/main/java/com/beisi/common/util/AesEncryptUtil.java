package com.beisi.common.util;

/**
 * AES 128bit 加密解密工具类
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 配置文件读取工具类
 * 
 */
public class AesEncryptUtil {
	
	
	/**
	 * 加密方法
	 * @param data  要加密的数据
	 * @param key 加密key
	 * @param iv 加密iv
	 * @return 加密的结果
	 * @throws Exception
	 */
	public static String encrypt(String data, String key, String iv) throws Exception {
		try {

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}

			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);

			return new Base64().encodeToString(encrypted);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密方法
	 * @param data 要解密的数据
	 * @param key  解密key
	 * @param iv 解密iv
	 * @return 解密的结果
	 * @throws Exception
	 */
	public static String desEncrypt(String data, String key, String iv) throws Exception {
		try {
			byte[] encrypted1 = new Base64().decode(data);

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	* 测试
	*/
	public static void main(String args[]) throws Exception {

		String test = "18729990110";

		String data = null;
		String key = "dufy20170329java";
		String iv = "dufy20170329java";

		data = encrypt(test, key, iv);

		System.out.println(data);
		System.out.println(desEncrypt(data, key, iv));
	}
	
}