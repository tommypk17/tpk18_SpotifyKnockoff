package com.tpk18.SpotifyKnockoff;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
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
	
	protected JLabel labelSelectView;
	protected ButtonGroup buttonGroup;
	protected JRadioButton buttonRadio;
	protected ArrayList<JRadioButton> buttonRadios;
	protected JPanel panelRadioButtons;
	
	protected JMenuItem menuItem;
	protected JPopupMenu popUpMenu;
	
	protected String[] buttonTitles = {"Search", "Cancel", "Add New Entry", "Delete"};
	protected String[] buttonRadioNames = {"Album","Artist", "Song"};
	
	
	public MainPanel(){
//		setting up basic layout of MainPanel
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
//		adding search bar
		textSearch = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(textSearch, c);
		
//		adding radio buttons
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 1;
		panelRadioButtons = new JPanel();
		labelSelectView = new JLabel("Select View");
		panelRadioButtons.setLayout(new BoxLayout(panelRadioButtons, BoxLayout.Y_AXIS));
		panelRadioButtons.add(labelSelectView);
		buttonGroup = new ButtonGroup();
		
		buttonRadios = new ArrayList<>();
		for(int i = 0; i < buttonRadioNames.length; i++) {
			
			buttonRadio = new JRadioButton(buttonRadioNames[i]);
			buttonRadio.setActionCommand(buttonRadioNames[i]);
			buttonRadios.add(buttonRadio);
			buttonGroup.add(buttonRadio);
			panelRadioButtons.add(buttonRadio);
			if(buttonRadio.getActionCommand() == "Album")buttonRadio.setSelected(true);
		}
		add(panelRadioButtons, c);

//		adding table
		c.weightx = 1;
		c.weighty = 1;
		tableDataTable = new JTable(tableDataModel);
		tableDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		textScrollStorage = new JScrollPane(tableDataTable);
		textScrollStorage.setPreferredSize(new Dimension(600, 400));
		textScrollStorage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(textScrollStorage, c);

//		adding search button
		c.gridwidth = 1;
		buttonSearch = new JButton(buttonTitles[0]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 0;
		add(buttonSearch, c);
		
//		adding cancel button
		c.gridwidth = 1;
		buttonCancel = new JButton(buttonTitles[1]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 2;
		add(buttonCancel, c);
		
//		adding new entry button
//		buttonAddNew = new JButton(buttonTitles[2]);
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 2;
//		add(buttonAddNew, c);
//		
//		adding delete button
//		buttonDelete = new JButton(buttonTitles[3]);
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 1;
//		c.gridy = 2;
//		buttonDelete.setEnabled(false);
//		add(buttonDelete, c);	
		
		
		
//		menuItem = new JMenuItem("Copy Password");
		
	}

	/**
	 * 
	 * @return JButton - returns search button object
	 */
	public JButton getButtonSearch() {
		return buttonSearch;
	}
	/**
	 * 
	 * @return JButton - returns cancel button object
	 */
	public JButton getButtonCancel(){
		return buttonCancel;
	}
	/**
	 * 
	 * @return JButton - returns add new item button object
	 */
	public JButton getButtonAddNew() {
		return buttonAddNew;
	}
	/**
	 * 
	 * @return JButton - returns delete button object
	 */
	public JButton getButtonDelete() {
		return buttonDelete;
	}
	/**
	 * 
	 * @return JTextField - returns search box object
	 */
	public JTextField getTextSearch() {
		return textSearch;
	}
	/**
	 * 
	 * @return JMenuItem - returns MenuItem object
	 */
	public JMenuItem getMenuItem(){
		return menuItem;
	}
	/**
	 * 
	 * @return void - resets text back to empty string
	 */
	public void resetTextSearch(){
		textSearch.setText("");
	}
	/**
	 * 
	 * @return ButtonGroup - returns ButtonGroup object for radio buttons
	 */
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	/**
	 * 
	 * @return JRadioButton - returns JRadioButton object
	 */
	public ArrayList<JRadioButton> getButtonRadios() {
		return buttonRadios;
	}
	/**
	 * @param DefaultTableModel - takes a DefaultTableModel
	 * @return void - sets DataModel
	 */
	public void setTableDataModel(DefaultTableModel tableDataModel) {
		this.tableDataModel = tableDataModel;
		tableDataTable.setModel(this.tableDataModel);
	}
	/**
	 *
	 * @return DefaultTableMode - gets DataModel object
	 */
	public DefaultTableModel getTableDataModel() {
		return tableDataModel;
	}
	/**
	 * 
	 * @return JTable - returns JTable object
	 */
	public JTable getTable(){
		return this.tableDataTable;
	}
}