package com.tpk18.SpotifyKnockoff;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
@SuppressWarnings("serial")
public class NewEntryPanel extends JPanel{
	
	private JLabel labelLine;
	private String[] labels = {"item1","item2","item3"};
	
	protected JTextField textBox;
	
	protected JButton buttonGenerate;
	protected JButton buttonAdd;
	protected JButton buttonCancel;
	
	public NewEntryPanel(){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.insets = new Insets(10,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		for(int i = 0; i < labels.length; i++){
			labelLine = new JLabel(labels[i]);
			c.gridx = 0;
			c.gridy = i;
			add(labelLine, c);
			
			textBox = new JTextField();
			textBox.setName(labels[i]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = i;
			add(textBox, c);
		}
		
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
		return textBox;
	}
	public void setFields(String[] fieldNames){
		this.labels = fieldNames;
	}


	
}
