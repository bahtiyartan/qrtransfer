package com.yedibit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShowImage extends JPanel {

	BufferedImage image;

	public ShowImage(String p_strPath) {
		this.reload(p_strPath);
	}

	public void reload(String p_strPath) {
		try {
			File input = new File(p_strPath);
			image = ImageIO.read(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {

		int nX = (this.getWidth() - image.getWidth()) / 2;
		int nY = (this.getHeight() - image.getHeight()) / 2;

		g.drawImage(image, nX, nY, null);
	}

}
