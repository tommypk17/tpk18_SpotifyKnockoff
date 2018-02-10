package com.tpk18.SpotifyKnockoff;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;


@SuppressWarnings("serial")
public class LoginPanel extends JPanel{
	
	private JLabel labelUserName;
	private JLabel labelPassword;
	
	protected JTextField textUserName;
	protected JPasswordField textPassword;
	
	protected JButton buttonLogin;
	protected JButton buttonCancel;
	
	public LoginPanel(){
		
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		labelUserName = new JLabel("Username:");
		c.gridx = 0;
		c.gridy = 0;
		add(labelUserName, c);
		
		labelPassword = new JLabel("Password:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(labelPassword, c);
		
		textUserName = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		add(textUserName, c);
		
		textPassword = new JPasswordField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		add(textPassword, c);
		
		buttonLogin = new JButton("Login");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(buttonLogin, c);
		
		buttonCancel = new JButton("Cancel");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		add(buttonCancel, c);

		
	}

	public JLabel getLabelPassword() {
		return labelPassword;
	}

	public JButton getButtonLogin() {
		return buttonLogin;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}
	public JTextField getTextUserName() {
		return textUserName;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}
}
