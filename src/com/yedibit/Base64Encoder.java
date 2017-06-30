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
}
