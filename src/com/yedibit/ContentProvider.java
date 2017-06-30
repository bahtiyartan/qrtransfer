package com.yedibit;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Vector;

public class ContentProvider {

	public static Vector<String> getContent() throws Exception {

		// read clipboard
		String strText = getClipboard();

		// compress
		String strCompressed = Base64Encoder.byteToBase64((new Compressor()).compress(strText.getBytes("UTF-8")));

		System.out.println(">>" + strText.length() + " chars reduced to " + strCompressed.length() + " chars");

		Vector<String> vParts = createArray(strCompressed);

		return vParts;

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

	private static Vector<String> createArray(String p_strValue) {

		int maxChar = 1200;

		Vector<String> vParts = new Vector<String>();

		int nCurrent = 0;

		while (true) {
			if (nCurrent + maxChar >= p_strValue.length()) {
				vParts.add(p_strValue.substring(nCurrent));
				break;
			} else {
				vParts.add(p_strValue.substring(nCurrent, nCurrent + maxChar));
				nCurrent = nCurrent + maxChar;
			}
		}

		return vParts;
	}

}
