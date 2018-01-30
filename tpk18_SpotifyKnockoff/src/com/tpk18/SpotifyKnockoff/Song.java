package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;



public class Song {
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	private String albumID;
	private Map<String, Artist> songArtists;
	
	/**
	 * Alternate Constructor - creates a new Artist, used to pull data from DB and sets instance variables with that data.
	 * @param songID - string value of the song id
	 */
	public Song(String songID) {
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				this.albumID = rs.getString("fk_album_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	/**
	 * Constructor - creates a new Song with parameters, used to add new Song to database.
	 * @param title - string value of the song title
	 * @param length - double value of the song length of time
	 * @param releaseDate  - string value of the song release date
	 * @param recordDate - string value of the song's date recorded
	 */
	public Song(String title, double length, String releaseDate, String recordDate, String albumID) {
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		this.albumID = albumID;
		
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2,  this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, this.filePath);
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.setString(7, this.albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Destroys all data about this object
	 */
	private void destroyObject(){
		this.songID = null;
		this.title = null;
		this.length = Integer.valueOf(null);
		this.filePath = null;
		this.releaseDate = null;
		this.recordDate = null;
		this.songArtists = null;
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
		destroyObject();
	}
    /**
     * This method is used to add a specific Artist to this Song's Artist MapList
     * @param artist - Artist Object
     */
	public void addArtist(Artist artist) {
		this.songArtists.put(artist.getArtistID(), artist);
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
     * @return Album ID of this Song Instance
     */
	public String getAlbumID() {
		return albumID;
	}
    /**
     * @return MapList of Artists for Song Instance
     */
	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
	/**
	 * Sets the album id for this song
	 * the value is inserted into the corresponding song in DB Table
	 * @param albumID - string value of album id
	 */
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
		String sql = "UPDATE song SET fk_album_id WHERE song_id='"+this.songID+"';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
		db = null;
	}
	
	
}