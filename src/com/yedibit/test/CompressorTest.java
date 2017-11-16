package com.yedibit.test;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.yedibit.Base64Encoder;

public class CompressorTest {

	@Test
	public void testCompression() {
		
		String str = "Hello World";
		String strFinal = "";
		
			
		try {
			String strBase64 = Base64Encoder.byteToBase64(str.getBytes("UTF-8"));
			byte[] bDeCompressed = Base64Encoder.base64ToByte(strBase64);
			strFinal = new String(bDeCompressed, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(str, strFinal);
	}
	
	@Test
	public void testDecompression() {
		
		String str = "Hello World";		
		assertEquals(str, "");
	}

}
