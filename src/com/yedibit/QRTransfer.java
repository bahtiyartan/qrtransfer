
package com.yedibit;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class QRTransfer {

	public static JFrame iFrame = new JFrame("QR Transfer");

	public static void main(String... strings) throws Exception {

		Configuration iConfig = new Configuration(strings);
		iConfig.printCurrentConfig();

		Content iContent = ContentProvider.getContent(iConfig);

		int nHeight = BarcodeCreator.createBarcode(iContent);

		iFrame.setSize((nHeight + 40) * iContent.Parts.size(), nHeight);

		JPanel iPanel = new JPanel(new GridLayout(1, iContent.Parts.size(), 50, 5));
		iPanel.setBackground(Color.WHITE);

		for (int i = 0; i < iContent.Parts.size(); i++) {
			ShowImage iImagePanel = new ShowImage("mytag" + i + ".png");
			NumberPane iNumberPane = new NumberPane(iImagePanel, i + 1, iContent.Parts.size());
			iPanel.add(iNumberPane);
		}

		iFrame.setContentPane(iPanel);
		iFrame.setUndecorated(true);
		iFrame.setBackground(Color.white);
		iFrame.setVisible(true);
		iFrame.setLocationRelativeTo(null);
		iFrame.setAlwaysOnTop(true);

		int nXLocation = 10;
		int nYLocation = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - nHeight - 100);

		iFrame.setLocation(nXLocation, nYLocation);

		iFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				iFrame.dispose();
			}
		});

		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
