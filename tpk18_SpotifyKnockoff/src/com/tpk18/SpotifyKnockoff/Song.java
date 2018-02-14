package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Thomas P. Kovalchuk
 * @version 1.0
 */
public class Song {
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private Map<String, Artist> songArtists = new HashMap<>();
	
	/**
	 * Alternate Constructor - creates a new Artist, used to pull data from DB and sets instance variables with that data.
	 * @param songID - string value of the song id
	 */
	public Song(String songID) {
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		DbUtilities db;
		ResultSet rs;
		try {
			db = new DbUtilities();
			rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				this.title = rs.getString("title");
				this.filePath = rs.getString("file_path");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
			}
			sql = "SELECT * FROM artist LEFT JOIN song_artist ON artist.artist_id = song_artist.fk_artist_id WHERE song_artist.fk_song_id='"+ songID + "';";
			rs = db.getResultSet(sql);
			while(rs.next()){
				Artist artist = new Artist(rs.getString("artist_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString( "band_name"));
				this.songArtists.put(artist.getArtistID(), artist);
				artist = null;
			}
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}finally{
			db = null;
			rs = null;
		}
				
	}
	/**
	 * Constructor - creates a new Song with parameters, used to add new Song to database.
	 * @param title - string value of the song title
	 * @param length - double value of the song length of time
	 * @param releaseDate  - string value of the song release date
	 * @param recordDate - string value of the song's date recorded
	 */
	public Song(String title, double length, String releaseDate, String recordDate) {
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		
		String sql = "INSERT INTO song (song_id,title,length,release_date,record_date) ";
		sql += "VALUES (?, ?, ?, ?, ?);";
		PreparedStatement ps;
		Connection conn;
		DbUtilities db;
		try {
			db = new DbUtilities();
			conn = db.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2,  this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, this.releaseDate);
			ps.setString(5, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}finally{
			db = null;
			ps = null;
			conn = null;
		}
	}
	/**
	 * Alternate Constructor - creates a new Artist object. No db connection.
	 * @param songID - string value of the song id
	 * @param title - string value of the song title
	 * @param length - double value of the song length of time
	 * @param releaseDate  - string value of the song release date
	 * @param recordDate - string value of the song's date recorded
	 */
	public Song(String songID, String title, double length, String releaseDate, String recordDate) {
				this.songID = songID;
				this.title = title;
				this.releaseDate = releaseDate;
				this.recordDate = recordDate;
				this.length = length;

				songArtists = new Hashtable<>();
	}

    /**
     * This method is used to remove a specific Song based on the songID as a String
     * @param songID - song id as String
     */
	public void deleteSong(String songID) {
		String sql = "DELETE FROM song_artist WHERE fk_song_id='"+songID+"';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		sql = "DELETE FROM song WHERE song_id='"+songID+"';";
		db.executeQuery(sql);
		db.closeDbConnection();
		//delete song object
	}
    /**
     * This method is used to add a specific Artist to this Song's Artist MapList
     * @param artist - Artist Object
     */
	public void addArtist(Artist artist) {
		this.songArtists.put(artist.getArtistID(), artist);
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES (?,?);";
		DbUtilities db;
		Connection conn;
		PreparedStatement ps;
		try {
			db = new DbUtilities();
			conn = db.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(1, artist.getArtistID());
			ps.executeUpdate();
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}finally{
			db = null;
			conn = null;
			ps = null;
		}
		
		
	}
    /**
     * This method is used to remove a specific Artist from this Song's Artist MapList
     * @param artist - Artist Object
     */
	public void deleteArtist(Artist artist) {
		this.songArtists.remove(artist.getArtistID());
	}
    /**
     * This method is used to remove a specific Artist based on the artistID as a String from this Song's Artist MapList
     * @param artistID - artist id as String
     */
	public void deleteArtist(String artistID) {
		this.songArtists.remove(artistID);
	}
	
	
    /**
     * @return ID of this Song Instance
     */
	public String getSongID() {
		return songID;
	}
    /**
     * @return Title of this Song Instance
     */
	public String getTitle() {
		return title;
	}
    /**
     * @return Length of Time of this Song Instance
     */
	public double getLength() {
		return length;
	}
    /**
     * @return File Path  of this Song Instance
     */
	public String getFilePath() {
		return filePath;
	}
    /**
     * @return Release of this Song Instance
     */
	public String getReleaseDate() {
		return releaseDate;
	}
    /**
     * @return Recording Date of this Song Instance
     */
	public String getRecordDate() {
		return recordDate;
	}
    /**
     * @return MapList of Artists for Song Instance
     */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	/**
	 * 
	 * @return Object[] - returns Object[] containing Song instance variables
	 */
	public Object[] toArray(){
		return new Object[] {this.songID, this.title, this.length, this.filePath, this.releaseDate, this.recordDate};
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = (?) WHERE song_id = '"+this.songID+"';";
		DbUtilities db;
		Connection conn;
		PreparedStatement ps;
		try {
			db = new DbUtilities();
			conn = db.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, filePath);
			ps.executeUpdate();
			db.closeDbConnection();
		} catch (Exception e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}finally{
			db = null;
		}
	}
	
	
}
