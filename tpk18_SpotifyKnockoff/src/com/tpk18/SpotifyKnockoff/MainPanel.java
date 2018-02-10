package com.tpk18.SpotifyKnockoff;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class MainPanel extends JPanel{	
	protected JTextField textSearch;
	protected JScrollPane textScrollStorage;
	protected JTable tableDataTable;
	protected DefaultTableModel tableDataModel;
	
	protected JButton buttonSearch;
	protected JButton buttonCancel;
	protected JButton buttonAddNew;
	protected JButton buttonDelete;
	
	protected ButtonGroup buttonGroup;
	protected JRadioButton buttonRadio;
	
	protected JMenuItem menuItem;
	protected JPopupMenu popUpMenu;
	
	public MainPanel(){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		textSearch = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(textSearch, c);
		
		buttonGroup = new ButtonGroup();
		String[] buttonNames = {"Artist", "Album", "Song"};
		for(int i = 0; i < buttonNames.length-1; i++) {
			buttonRadio = new JRadioButton(buttonNames[i]);
			buttonGroup.add(buttonRadio);
			c.weighty = 0;
			c.gridx = 3;
			c.gridy = 1;
			add(buttonRadio, c);
		}
		
		tableDataModel = new DefaultTableModel();
		tableDataModel.addColumn("row1");
		tableDataModel.addColumn("row2");
		tableDataModel.addColumn("row3");

		c.weighty = 1;
		tableDataTable = new JTable(tableDataModel);
		textScrollStorage = new JScrollPane(tableDataTable);
		textScrollStorage.setPreferredSize(new Dimension(400, 300));
		textScrollStorage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(textScrollStorage, c);

		c.gridwidth = 1;
		buttonSearch = new JButton("Search");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 0;
		add(buttonSearch, c);
		
		c.gridwidth = 1;
		buttonCancel = new JButton("Cancel");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 2;
		add(buttonCancel, c);
		
		
		buttonAddNew = new JButton("Add New Account");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(buttonAddNew, c);
		
		buttonDelete = new JButton("Delete");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		buttonDelete.setEnabled(false);
		add(buttonDelete, c);	
		
		
		
		menuItem = new JMenuItem("Copy Password");
		
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}
	
	public JButton getButtonCancel(){
		return buttonCancel;
	}
	
	public JButton getButtonAddNew() {
		return buttonAddNew;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public JTextField getTextSearch() {
		return textSearch;
	}
	public JMenuItem getMenuItem(){
		return menuItem;
	}
	public void resetTextSearch(){
		textSearch.setText("");
	}
}