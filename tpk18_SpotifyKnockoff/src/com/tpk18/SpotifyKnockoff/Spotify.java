package com.tpk18.SpotifyKnockoff;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.AbstractBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class Spotify extends JFrame{
	protected MainPanel panelMain;
	protected LoginPanel panelLogin;
	protected NewEntryPanel panelNewEntry;
	
	
	//need to add try/catch to prevent bad connection to db
	
	public Spotify(){
		setTitle("Spotify KnockOff");
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		
		panelMain = new MainPanel();
		panelLogin = new LoginPanel();
		panelNewEntry = new NewEntryPanel();
		
		getRootPane().setDefaultButton(this.getMainPanel().buttonSearch);
		
		add(panelLogin);
		setVisible(true);
		setInitSize();
	}
	private void setInitSize(){
		Dimension windowSize = new Dimension(600, 600);
		setSize(windowSize);
		pack();
	}
	/**
	 * @return void - resets frame size and repacks components
	 */
	private void resetSize(){
		pack();
	}
	/**
	 * 
	 * @return MainPanel - returns MainPanel object
	 */
	private MainPanel getMainPanel(){
		return this.panelMain;
	}
	/**
	 * 
	 * @return MainPanel - returns LoginPanel object
	 */
	private LoginPanel getLoginPanel(){
		return this.panelLogin;
	}
	/**
	 * 
	 * @return MainPanel - returns NewEntry object
	 */
	private NewEntryPanel getNewEntryPanel(){
		return this.panelNewEntry;
	}
	/**
	 * @return void - adds listener object to all buttons in all panels
	 */
	private void addSpotifyListener(){
		ActionListener listener = new Listener();
		MouseListener tableListener = new MouseListen();
		panelLogin.buttonLogin.addActionListener(listener);
		panelLogin.buttonCancel.addActionListener(listener);
		panelNewEntry.buttonGenerate.addActionListener(listener); 
		panelNewEntry.buttonAdd.addActionListener(listener); 
		panelNewEntry.buttonCancel.addActionListener(listener);
		panelMain.buttonSearch.addActionListener(listener); 
		panelMain.buttonCancel.addActionListener(listener);
		panelMain.buttonAddNew.addActionListener(listener); 
		panelMain.buttonDelete.addActionListener(listener);
		panelMain.tableDataTable.addMouseListener(tableListener);

		Enumeration<AbstractButton> elements = panelMain.buttonGroup.getElements();
		while(elements.hasMoreElements()){
			JRadioButton button = (JRadioButton) elements.nextElement();
			button.addActionListener(listener);
			
		}
		
	}

	/**
	 * 
	 * @param args - no arguments needed. Instantiates new instance of Spotify
	 */
	public static void main(String[] args) {
		Spotify sp = new Spotify();
		sp.addSpotifyListener();


	}
	
	/**
	 * 
	 * @param searchTerm
	 * @return DefaultTableModel - returns DefaultTableModel for use with a JTable to display data from DB
	 */
	public DefaultTableModel searchSongs(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		DbUtilities dbu = new DbUtilities();
		String sql = "SELECT * FROM song";
		if(searchTerm.equals("")) {
			try {
				dm = dbu.getDataTable(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dm = dbu.getDataTable(sql+" WHERE title LIKE '%"+searchTerm+"%';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dm;
	}
	
	/**
	 * 
	 * @param searchTerm
	 * @return DefaultTableModel - returns DefaultTableModel for use with a JTable to display data from DB
	 */
	public DefaultTableModel searchAlbums(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		DbUtilities dbu = new DbUtilities();
		String sql = "SELECT * FROM album";
		if(searchTerm.equals("")) {
			try {
				dm = dbu.getDataTable(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dm = dbu.getDataTable(sql+" WHERE title LIKE '%"+searchTerm+"%';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dm;
	}
	
	/**
	 * 
	 * @param searchTerm
	 * @return DefaultTableModel - returns DefaultTableModel for use with a JTable to display data from DB
	 */
	public DefaultTableModel searchArtists(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		DbUtilities dbu = new DbUtilities();
		String sql = "SELECT * FROM artist";
		if(searchTerm.equals("")) {
			try {
				dm = dbu.getDataTable(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				dm = dbu.getDataTable(sql+" WHERE first_name LIKE '%"+searchTerm+"%' or last_name LIKE '%"+searchTerm+"%' or band_name LIKE '%"+searchTerm+"%';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dm;
	}
	public String getSelectedRadioCommand(){
		return getMainPanel().getButtonGroup().getSelection().getActionCommand();
	}
	
	public class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(getSelectedRadioCommand() == "Album"){
				System.out.println(new Album((String)getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0)).getTitle());
			}else if(getSelectedRadioCommand() == "Artist"){
				System.out.println(getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0));
			}else if(getSelectedRadioCommand() == "Song"){
				System.out.println(getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
		
	/**
	 * 
	 * @author Tommy
	 *
	 */
	public class Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			//
			//MainPanel Events
			//
			if(source == getMainPanel().getButtonSearch()){
				String command = getSelectedRadioCommand();
				DefaultTableModel tb = null;
					try{
						if(command == "Song"){
							tb = searchSongs(getMainPanel().getTextSearch().getText());
						}else if(command == "Artist"){
							tb = searchArtists(getMainPanel().getTextSearch().getText());
						}else if(command == "Album"){
							tb = searchAlbums(getMainPanel().getTextSearch().getText());
						}
						getMainPanel().setTableDataModel(tb);
						getMainPanel().getButtonDelete().setEnabled(true);
					}catch(NullPointerException pointerException){
						JOptionPane.showMessageDialog(panelMain, "Could not find specified entry");
						getMainPanel().getButtonDelete().setEnabled(false);
					}

			}
			else if(source == getMainPanel().getButtonCancel()){
//				view.getMainPanel().setListAccounts(model.getAccounts());
				getMainPanel().getButtonDelete().setEnabled(false);
				getMainPanel().resetTextSearch();
			}
			else if(source == getMainPanel().getButtonAddNew()){
				remove(getMainPanel());
				add(getNewEntryPanel());
				String command = getSelectedRadioCommand();
				if(command == "Artist"){
					
					String[] array = {"Title", "Recording Company"};
					getNewEntryPanel().setFields(array);
					
				}else if(command == "Album"){
					String[] array = {"Title", "Recording Company"};
					getNewEntryPanel().setFields(array);
					
				}else if(command == "Song"){
					String[] array = {"Title", "Recording Company"};
					getNewEntryPanel().setFields(array);
				}
				resetSize();

			}else if(source == getMainPanel().getButtonDelete()){
//				Account accountToDelete = model.getSelectedAccount();
//				int opt = JOptionPane.showConfirmDialog(panelMain, "Are you sure you would like to delete the following acount?\n"+ accountToDelete);
//				if(opt == 0){
//					model.deleteAccount(accountToDelete);
//					view.getMainPanel().setListAccounts(model.getAccounts());
//				JOptionPane.showMessageDialog(panelMain, "Account Deleted!");
//					getMainPanel().resetTextSearch();
//					getMainPanel().getButtonDelete().setEnabled(false);
//				}
			}else if((getMainPanel().getButtonRadios().contains(source))){
				String command = getMainPanel().getButtonGroup().getSelection().getActionCommand();
				DefaultTableModel tb = null;
				if(command == "Artist"){
					tb = searchArtists("");
				}else if(command == "Album"){
					tb = searchAlbums("");
				}else if(command == "Song"){
					tb = searchSongs("");
					
				}
				getMainPanel().setTableDataModel(tb);
			}
			
			//
			//NewAccountPanel Events
			//
			else if(source == panelNewEntry.getButtonGenerate()){
//				getNewAccountPanel().getTextPassword().setText(PassGen.generatePassword());
				
			}else if(source == panelNewEntry.getButtonAdd()){

//					String siteName = getNewEntryPanel().getTextSite().getText();
//					String userName = getNewEntryPanel().getTextUserName().getText();
//					String passWord = getNewEntryPanel().getTextPassword().getText();
//					if(siteName.equals("")){
//						JOptionPane.showMessageDialog(panelNewEntry, "Please enter a Site name");
//					}if(userName.equals("")){
//						JOptionPane.showMessageDialog(panelNewEntry, "Please enter a Username ");
//					}if(passWord.equals("")){
//						JOptionPane.showMessageDialog(panelNewEntry, "Please enter a Password");
//					}if(!siteName.equals("")&&!userName.equals("")&&!passWord.equals("")){
//					Account newAccount = new Account(siteName, userName, passWord);
//					addAccount(newAccount);

//					JOptionPane.showMessageDialog(this, "Here is your account info:\n"+newAccount.toString());
//					}

			
			}else if(source == getNewEntryPanel().getButtonCancel()){
				remove(getNewEntryPanel());
				add(getMainPanel());
//				getMainPanel().setListAccounts(null);
				resetSize();
			
			}
			//
			//LoginPanel Events
			//
			else if(source == getLoginPanel().getButtonLogin()){
				remove(getLoginPanel());
				add(getMainPanel());
				DefaultTableModel tb = null;
				tb = searchAlbums("");
				getMainPanel().setTableDataModel(tb);
				resetSize();
				/*
				if(model.login(view.getLoginPanel().getTextUserName().getText(), view.getLoginPanel().getTextPassword().getText())){
					view.remove(view.getLoginPanel());
					view.add(view.getMainPanel());
					view.getMainPanel().setTextStorage(model.listAccounts());
					view.resetSize();
				}else{
					JOptionPane.showMessageDialog(view, "Incorrect Username or Password");
				}*/
				

			}else if(source == getLoginPanel().getButtonCancel()){
				System.exit(0);
			}
			
			
		}
	}


}
