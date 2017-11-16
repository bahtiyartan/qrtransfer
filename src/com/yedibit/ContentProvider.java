package com.yedibit;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Vector;

public class ContentProvider {

	public static Content getContent(Configuration pConfig) throws Exception {

		// read clipboard
		String strText = getClipboard();
		
		

		if (pConfig.UseCompression) {
			// compress
			String strCompressed = Base64Encoder.byteToBase64((new Compressor()).compress(strText.getBytes("UTF-8")));
			System.out.println(">>" + strText.length() + " chars reduced to " + strCompressed.length() + " chars");
			strText = strCompressed;
		}

		Vector<String> vParts = createArray(strText, pConfig);

		return new Content(vParts, Base64Encoder.MD5(strText));

	}

	private static String getClipboard() {
		Clipboard iClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable iContent = iClipboard.getContents(null);
		String strResult = "";
		boolean hasTransferableText = (iContent != null) && iContent.isDataFlavorSupported(DataFlavor.stringFlavor);

		if (hasTransferableText) {
			try {
				strResult = (String) iContent.getTransferData(DataFlavor.stringFlavor);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return strResult;
	}

	private static Vector<String> createArray(String p_strValue, Configuration pConfig) {

		int nMaxCharCount = pConfig.MaxChar;

		Vector<String> vParts = new Vector<String>();

		int nCurrent = 0;

		while (true) {
			if (nCurrent + nMaxCharCount >= p_strValue.length()) {
				vParts.add(p_strValue.substring(nCurrent));
				break;
			} else {
				vParts.add(p_strValue.substring(nCurrent, nCurrent + nMaxCharCount));
				nCurrent = nCurrent + nMaxCharCount;
			}
		}

		return vParts;
	}

}
