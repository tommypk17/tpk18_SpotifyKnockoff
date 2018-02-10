package com.tpk18.SpotifyKnockoff;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;


@SuppressWarnings("serial")
public class NewEntryPanel extends JPanel{
	
	private JLabel labelSite;
	private JLabel labelUserName;
	private JLabel labelPassword;
	
	protected JTextField textSite;
	protected JTextField textUserName;
	protected JTextField textPassword;
	
	protected JButton buttonGenerate;
	protected JButton buttonAdd;
	protected JButton buttonCancel;
	
	public NewEntryPanel(){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		labelSite = new JLabel("Site:");
		c.gridx = 0;
		c.gridy = 0;
		add(labelSite, c);
		
		labelUserName = new JLabel("Username:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(labelUserName, c);
		
		labelPassword = new JLabel("Password:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(labelPassword, c);
		
		textSite = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		add(textSite, c);
		
		textUserName = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		add(textUserName, c);
		
		textPassword = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		add(textPassword, c);
		
		buttonGenerate = new JButton("Generate");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(buttonGenerate, c);
		
		buttonAdd = new JButton("Add");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		add(buttonAdd, c);
		
		buttonCancel = new JButton("Cancel");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		add(buttonCancel, c);

		
	}

	public JButton getButtonGenerate() {
		return buttonGenerate;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}
	public JTextField getTextSite() {
		return textSite;
	}

	public JTextField getTextUserName() {
		return textUserName;
	}

	public void setTextSite(String textSite) {
		this.textSite.setText(textSite);
	}

	public void setTextUserName(String textUserName) {
		this.textUserName.setText(textUserName);
	}
	public void setTextPassword(String textPassword) {
		this.textPassword.setText(textPassword);
	}
	
}
