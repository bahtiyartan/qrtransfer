package com.yedibit;

import java.io.IOException;

public class Base64Encoder {

	public static String byteToBase64(byte[] p_bInput) {
		return new sun.misc.BASE64Encoder().encode(p_bInput);
	}

	public static byte[] base64ToByte(String p_strInput) {
		try {
			return new sun.misc.BASE64Decoder().decodeBuffer(p_strInput);
		} catch (IOException e) {
			return new byte[0];
		}
	}

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
