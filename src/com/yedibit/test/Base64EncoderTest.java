package com.yedibit.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.yedibit.Compressor;

public class Base64EncoderTest {

	@Test
	public void test() {
		
		String str = "Hello World";
		String strFinal = "";
		
		Compressor iCompressor = new Compressor();
		
		try {
			byte[] bCompressed = iCompressor.compress(str.getBytes("UTF-8"));
			byte[] bDeCompressed = iCompressor.decompress(bCompressed);
			strFinal = new String(bDeCompressed, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(str, strFinal);
	}

}
