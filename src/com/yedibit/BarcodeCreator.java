package com.yedibit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;

public class BarcodeCreator {

	public static int createBarcode(Vector<String> pTokens) throws Exception {

		int nMaxHeight = 0;
		int nMaxWidth = 0;

		for (int i = 0; i < pTokens.size(); i++) {
			
			String strValue = pTokens.get(i);
			
			String strPrefix = (i+1) + "/" + pTokens.size() + "/T|";
			strValue = strPrefix + strValue;	
			
			BufferedImage jBufferedImage = null;

			Map<EncodeHintType, Object> map = new HashMap<EncodeHintType, Object>();

			map.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			BarcodeQRCode jBarcodeQRCode = new BarcodeQRCode(strValue, 0, 0, map);
			Image jImage = jBarcodeQRCode.createAwtImage(Color.black, Color.white);

			jBufferedImage = new BufferedImage(jImage.getWidth(null) * 2, jImage.getHeight(null) * 2, BufferedImage.TYPE_INT_RGB);
			Graphics2D jGraphics2D = jBufferedImage.createGraphics();
			jGraphics2D.setColor(Color.red);
			jGraphics2D.drawRect(0, 0, 10, 10);
			jGraphics2D.drawImage(jImage, 0, 0, jImage.getWidth(null) * 2, jImage.getHeight(null) * 2, null);

			if (nMaxHeight < (jImage.getHeight(null) * 2 + 50)) {
				nMaxHeight = (jImage.getHeight(null) * 2 + 50);
			}

			if (nMaxWidth < (jImage.getWidth(null) * 2)) {
				nMaxWidth = (jImage.getWidth(null) * 2);
			}

			jGraphics2D.dispose();
			FileOutputStream jFileOut = new FileOutputStream("mytag" + i + ".png");
			javax.imageio.ImageIO.write(jBufferedImage, "png", jFileOut);
			jFileOut.flush();
			jFileOut.close();
			jBufferedImage.flush();
		}

		return nMaxHeight;
	}
}
