package com.tpk18.SpotifyKnockoff;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class Spotify extends JFrame{
	protected MainPanel panelMain;
	protected LoginPanel panelLogin;
	protected NewEntryPanel panelNewAccount;
	
	public Spotify(){
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		panelMain = new MainPanel();
		panelLogin = new LoginPanel();
		panelNewAccount = new NewEntryPanel();
		
		getRootPane().setDefaultButton(this.getMainPanel().buttonSearch);
		
		add(panelLogin);
		setVisible(true);
		resetSize();
	}
	public void resetSize(){
		pack();
		Dimension windowSize = new Dimension(600, 600);
		setSize(windowSize);
	}
	public MainPanel getMainPanel(){
		return this.panelMain;
	}
	public LoginPanel getLoginPanel(){
		return this.panelLogin;
	}
	public NewEntryPanel getNewAccountPanel(){
		return this.panelNewAccount;
	}
	public void addSpotifyListener(){
		ActionListener listener = new Listener();
		panelLogin.buttonLogin.addActionListener(listener);
		panelLogin.buttonCancel.addActionListener(listener);
		panelNewAccount.buttonGenerate.addActionListener(listener); 
		panelNewAccount.buttonAdd.addActionListener(listener); 
		panelNewAccount.buttonCancel.addActionListener(listener);
		panelMain.buttonSearch.addActionListener(listener); 
		panelMain.buttonCancel.addActionListener(listener);
		panelMain.buttonAddNew.addActionListener(listener); 
		panelMain.buttonDelete.addActionListener(listener);
		
		
	}


	public static void main(String[] args) {
		Spotify sp = new Spotify();
		sp.addSpotifyListener();


	}
	
	public DefaultTableModel searchSongs(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		if(searchTerm.equals(null)) {
			
		}
		return null;
	}
	public DefaultTableModel searchAlbums(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		if(searchTerm.equals(null)) {
			
		}
		return null;
	}
	public DefaultTableModel searchArtists(String searchTerm) {
		DefaultTableModel dm = new DefaultTableModel();
		if(searchTerm.equals(null)) {
			
		}
		return null;
	}
	
	
	
	public class TableListener implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			
			
		}
		
	}
	public class Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			//
			//MainPanel Events
			//
			if(source == getMainPanel().getButtonSearch()){
				if(getMainPanel().getTextSearch().getText().equals("")){
//					view.getMainPanel().setListAccounts(model.getAccounts());
					getMainPanel().getButtonDelete().setEnabled(false);
				}else{
					try{
//						view.getMainPanel().setListAccounts(model.searchAccounts(view.getMainPanel().getTextSearch().getText()));
						getMainPanel().getButtonDelete().setEnabled(true);
					}catch(NullPointerException pointerException){
						JOptionPane.showMessageDialog(panelMain, "Could not find specified site");
						getMainPanel().getButtonDelete().setEnabled(false);
					}
				}

			}
			else if(source == getMainPanel().getButtonCancel()){
//				view.getMainPanel().setListAccounts(model.getAccounts());
				getMainPanel().getButtonDelete().setEnabled(false);
				getMainPanel().resetTextSearch();
			}
			else if(source == getMainPanel().getButtonAddNew()){
				remove(getMainPanel());
				add(getNewAccountPanel());
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
			}
			//
			//NewAccountPanel Events
			//
			else if(source == getNewAccountPanel().getButtonGenerate()){
//				getNewAccountPanel().getTextPassword().setText(PassGen.generatePassword());
				
			}else if(source == getNewAccountPanel().getButtonAdd()){

					String siteName = getNewAccountPanel().getTextSite().getText();
					String userName = getNewAccountPanel().getTextUserName().getText();
					String passWord = getNewAccountPanel().getTextPassword().getText();
					if(siteName.equals("")){
						JOptionPane.showMessageDialog(panelNewAccount, "Please enter a Site name");
					}if(userName.equals("")){
						JOptionPane.showMessageDialog(panelNewAccount, "Please enter a Username ");
					}if(passWord.equals("")){
						JOptionPane.showMessageDialog(panelNewAccount, "Please enter a Password");
					}if(!siteName.equals("")&&!userName.equals("")&&!passWord.equals("")){
//					Account newAccount = new Account(siteName, userName, passWord);
//					addAccount(newAccount);
					getNewAccountPanel().setTextSite("");
					getNewAccountPanel().setTextPassword("");
					getNewAccountPanel().setTextUserName("");
//					JOptionPane.showMessageDialog(this, "Here is your account info:\n"+newAccount.toString());
					}

			
			}else if(source == getNewAccountPanel().getButtonCancel()){
				getNewAccountPanel().getTextSite().setText("");
				getNewAccountPanel().getTextUserName().setText("");
				getNewAccountPanel().getTextPassword().setText("");
				remove(getNewAccountPanel());
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
//				getMainPanel().setListAccounts(getAccounts());
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
