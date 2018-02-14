package com.tpk18.SpotifyKnockoff;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
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

		DefaultTableModel dm = new DefaultTableModel();
		dm = searchAlbums("");
		getMainPanel().setTableDataModel(dm);
//		panelLogin = new LoginPanel();
//		panelNewEntry = new NewEntryPanel();
		
		getRootPane().setDefaultButton(this.getMainPanel().buttonSearch);
		
		add(panelMain);
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
	@SuppressWarnings("unused")
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
	 * @return void - adds listener object to all buttons in all panels
	 */
	private void addSpotifyListener(){
		ActionListener listener = new Listener();
		MouseListener tableListener = new MouseListen();
//		panelLogin.buttonLogin.addActionListener(listener);
//		panelLogin.buttonCancel.addActionListener(listener);
//		panelNewEntry.buttonGenerate.addActionListener(listener); 
//		panelNewEntry.buttonAdd.addActionListener(listener); 
//		panelNewEntry.buttonCancel.addActionListener(listener);
		panelMain.buttonSearch.addActionListener(listener); 
		panelMain.buttonCancel.addActionListener(listener);
//		panelMain.buttonAddNew.addActionListener(listener); 
//		panelMain.buttonDelete.addActionListener(listener);
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
		DbUtilities dbu = null;
		String sql = "SELECT * FROM song";
		if(searchTerm.equals("")) {
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql);
				dbu.closeDbConnection();
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Check your Connection");
			} catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
			}
		}else{
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql+" WHERE title LIKE '%"+searchTerm+"%';");
				dbu.closeDbConnection();
			}catch(NullPointerException v){
				JOptionPane.showMessageDialog(panelMain, "Check your Connection");
			} catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
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
		DbUtilities dbu = null;
		String sql = "SELECT * FROM album";
		if(searchTerm.equals("")) {
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql);
				dbu.closeDbConnection();
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Check your Connection");
			} catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
			}
		}else{
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql+" WHERE title LIKE '%"+searchTerm+"%';");
				dbu.closeDbConnection();
			}catch(NullPointerException v){
				JOptionPane.showMessageDialog(panelMain, "Check your Connection");
			} catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
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
		DbUtilities dbu = null;
		String sql = "SELECT * FROM artist";
		if(searchTerm.equals("")) {
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql);
				dbu.closeDbConnection();
			}
			catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Check your Connection");
			}catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
			}
		}else{
			try {
				dbu = new DbUtilities();
				dm = dbu.getDataTable(sql+" WHERE first_name LIKE '%"+searchTerm+"%' or last_name LIKE '%"+searchTerm+"%' or band_name LIKE '%"+searchTerm+"%';");
				dbu.closeDbConnection();
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Check your Connection");
			} catch (SQLException e) {
				ErrorLogger.log(e.getMessage());
			}finally{
				dbu = null;
			}
		}
		return dm;
	}
	public String getSelectedRadioCommand(){
		return getMainPanel().getButtonGroup().getSelection().getActionCommand();
	}
	
	public class MouseListen implements MouseListener{

		//BONUS
		//Listener for table to view songs in an album or songs by an artist
		
		@Override
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel dm = null;
			if(getSelectedRadioCommand() == "Album"){
				String line = (String)getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0);
				Album selectedAlbum = new Album(line);
				dm = new DefaultTableModel();
				dm.setColumnIdentifiers(new Object[] {"song_id", "title", "length", "file_path", "release_date", "record_date"});
				Map<String, Song> table = selectedAlbum.getAlbumSongs();
				for(Entry<String, Song> entry: table.entrySet()){
					Song s = entry.getValue();
					dm.addRow(s.toArray());
					s = null;
				}
				
				getMainPanel().setTableDataModel(dm);
				
				
			}else if(getSelectedRadioCommand() == "Artist"){
				String line = (String)getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0);
				Artist selectedArtist = new Artist(line);
				dm = new DefaultTableModel();
				dm.setColumnIdentifiers(new Object[] {"song_id", "title", "length", "file_path", "release_date", "record_date"});
				Map<String, Song> table = selectedArtist.getArtistSongs();
				for(Entry<String, Song> entry: table.entrySet()){
					Song s = entry.getValue();
					dm.addRow(s.toArray());
					s = null;
				}
				
				
				getMainPanel().setTableDataModel(dm);

				
			}else if(getSelectedRadioCommand() == "Song"){
				String line = (String)getMainPanel().getTableDataModel().getValueAt(getMainPanel().getTable().getSelectedRow(), 0);
				Song selectedSong = new Song(line);
				dm = new DefaultTableModel();
				dm.setColumnIdentifiers(new Object[] {"artist_id", "first_name", "last_name", "band_name"});
				Map<String, Artist> table = selectedSong.getSongArtists();
				for(Entry<String, Artist> entry: table.entrySet()){
					Artist a = entry.getValue();
					dm.addRow(a.toArray());
					a = null;
				}
				
				getMainPanel().setTableDataModel(dm);
			}
			dm = null;
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
				DefaultTableModel dm = new DefaultTableModel();
				if(command == "Song"){
					dm = searchSongs(getMainPanel().getTextSearch().getText());
				}else if(command == "Artist"){
					dm = searchArtists(getMainPanel().getTextSearch().getText());
				}else if(command == "Album"){
					dm = searchAlbums(getMainPanel().getTextSearch().getText());
				}
				getMainPanel().setTableDataModel(dm);

				dm = null;

			}
			else if(source == getMainPanel().getButtonCancel()){
				String command = getSelectedRadioCommand();
				DefaultTableModel dm = new DefaultTableModel();
				if(command == "Artist"){
					dm = searchArtists("");
				}else if(command == "Album"){
					dm = searchAlbums("");
				}else if(command == "Song"){
					dm = searchSongs("");
				}

				getMainPanel().setTableDataModel(dm);
				getMainPanel().resetTextSearch();
				dm = null;
//			}
//			else if(source == getMainPanel().getButtonAddNew()){
//				remove(getMainPanel());
//				add(getNewEntryPanel());
//				String command = getSelectedRadioCommand();
//				if(command == "Artist"){
//					String[] array = {"Title", "Recording Company"};
//					getNewEntryPanel().setFields(array);
//					
//				}else if(command == "Album"){
//					String[] array = {"Title", "Recording Company"};
//					getNewEntryPanel().setFields(array);
//					
//				}else if(command == "Song"){
//					String[] array = {"Title", "Recording Company"};
//					getNewEntryPanel().setFields(array);
//				}
//				resetSize();

			}else if(source == getMainPanel().getButtonDelete()){

			}else if((getMainPanel().getButtonRadios().contains(source))){
				String command = getSelectedRadioCommand();
				DefaultTableModel dm = null;
				if(command == "Artist"){
					dm = searchArtists("");
				}else if(command == "Album"){
					dm = searchAlbums("");
				}else if(command == "Song"){
					dm = searchSongs("");
					
				}
				getMainPanel().setTableDataModel(dm);
				dm = null;
			}
//			
//			//
//			//NewEntryPanel Events
//			//
//			else if(source == panelNewEntry.getButtonGenerate()){
//
//				
//			}else if(source == panelNewEntry.getButtonAdd()){
//
//
//			
//			}else if(source == getNewEntryPanel().getButtonCancel()){
//				remove(getNewEntryPanel());
//				add(getMainPanel());
//				resetSize();
//			
//			}
			//
			//LoginPanel Events
			//
//			else if(source == getLoginPanel().getButtonLogin()){
//				remove(getLoginPanel());
//				add(getMainPanel());
//				DefaultTableModel dm = new DefaultTableModel();
//				DbUtilities db = new DbUtilities();
//				dm = searchAlbums("");
//				getMainPanel().setTableDataModel(dm);
//				
//				
//				dm = null;
//				resetSize();
//
//				
//
//			}else if(source == getLoginPanel().getButtonCancel()){
//				System.exit(0);
//			}
			
//			
		}
	}


}
