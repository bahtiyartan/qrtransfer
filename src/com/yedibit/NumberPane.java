package com.yedibit;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NumberPane extends JPanel {

	public NumberPane(ShowImage p_iImagePane, int p_nNo, int p_nTotal) {
		super(new BorderLayout());

		this.setBackground(Color.WHITE);

		this.add(p_iImagePane);

		JLabel jNumberLabel = new JLabel(p_nNo + "/" + p_nTotal);
		jNumberLabel.setFont(jNumberLabel.getFont().deriveFont(20f));
		jNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		jNumberLabel.setHorizontalAlignment(JLabel.CENTER);

		this.add(jNumberLabel, BorderLayout.SOUTH);
	}
}
