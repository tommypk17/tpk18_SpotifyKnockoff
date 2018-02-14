package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	private Map<String, Song> albumSongs = new HashMap<>();
	
	/**
	 * Constructor - creates a new Album with parameters, used to add new Album to database.
	 * @param title - string value of the album name
	 * @param releaseDate - string value of the album's release date
	 * @param recordingCompany - string value of the name of the recording company
	 * @param numberOfTracks - double value of the number of tracks in the album
	 * @param pmrcRating - string value of the PMRC Rating
	 * @param length - double value of the total play time of the album
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		this.albumID = UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumSongs = null;
		
		
		String sql = "INSERT INTO album (album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating, length) ";
		sql += "VALUES (?, ?, ?, ?,?,?,?,?);";
		DbUtilities db;
		PreparedStatement ps;
		Connection conn;
		try {
			db = new DbUtilities();
			conn = db.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2,  this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, this.coverImagePath);
			ps.setString(5, this.recordingCompany);
			ps.setInt(6,  this.numberOfTracks);
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
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
	 * Alternate Constructor - creates a new Album, used to pull data from DB and sets instance variables with that data.
	 * @param albumID - string value of the album id
	 */
	public Album(String albumID) {
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		DbUtilities db;
		ResultSet rs;
		try {
			db = new DbUtilities();
			rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.coverImagePath = rs.getString("cover_image_path");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.recordingCompany = rs.getString("recording_company_name");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
			}
			
			sql = "SELECT * FROM song LEFT JOIN album_song ON song.song_id = album_song.fk_song_id WHERE album_song.fk_album_id='"+ albumID + "';";
			rs = db.getResultSet(sql);
			while(rs.next()){
				Song song = new Song(rs.getString("song_id"), rs.getString("title"), rs.getDouble("length"), rs.getString( "release_date"), rs.getString( "record_date"));
				this.albumSongs.put(song.getSongID(), song);
				song = null;
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
	 * Alternative Constructor - creates a new Album Object with parameters. No DB connection
	 * @param albumID - string value of the album id
	 * @param title - string value of the album name
	 * @param releaseDate - string value of the album's release date
	 * @param recordingCompany - string value of the name of the recording company
	 * @param numberOfTracks - double value of the number of tracks in the album
	 * @param pmrcRating - string value of the PMRC Rating
	 * @param length - double value of the total play time of the album
	 */
	public Album(String albumID, String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		this.albumID = albumID;
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumSongs = new Hashtable<>();
	}
    /**
     * This method is used to remove a specific Album based on the albumID as a String
     * @param String - album id as String of album to delete
     */
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM album WHERE album_id='"+albumID+"';";
		Map<String, Song> it = this.getAlbumSongs();
		for(Entry<String, Song> entry: it.entrySet()){
			Song song = (Song)entry;
			song.deleteSong(song.getSongID());
		}
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}
    /**
     * This method is used to add a specific song to the Album list
     * @param Song - song object
     */
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES (?,?);";
		DbUtilities db;
		Connection conn;
		PreparedStatement ps;
		try {
			db = new DbUtilities();
			conn = db.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(1, song.getSongID());
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
     * This method is used to remove a specific song from the Album list
     * @param Song - song object to delete
     */
	public void deleteSong(Song song) {
		this.albumSongs.remove(song).getSongID();
	}
    /**
     * This method is used to remove a specific song from the Album list
     * @param String - song id as String
     */
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}	
    /**
     * @return String - ID of this Album Instance
     */
	public String getAlbumID() {
		return albumID;
	}
    /**
     * @return String - Title of this Album Instance
     */
	public String getTitle() {
		return title;
	}
    /**
     * @return String - Release Date of this Album Instance
     */
	public String getReleaseDate() {
		return releaseDate;
	}
    /**
     * @return String - Cover Image Path of this Album Instance
     */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	/**
	 * 
	 * @param String - coverImagePath gets set in Album Instance
	 */
    public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	/**
     * @return String - Recording Company Name of this Album Instance
     */
	public String getRecordingCompany() {
		return recordingCompany;
	}
    /**
     * @return int - Number of Tracks for this Album Instance
     */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
    /**
     * @return String - PMRC Rating of this Album Instance
     */
	public String getPmrcRating() {
		return pmrcRating;
	}
    /**
     * @return double - Length of time of this Album Instance
     */
	public double getLength() {
		return length;
	}
	/**
	 * 
	 * @return Object[] - returns Object[] containing Album instance variables
	 */
	public Object[] toArray(){
		return new Object[] {this.albumID, this.title, this.releaseDate, this.coverImagePath, this.recordingCompany, this.numberOfTracks, this.pmrcRating, this.length};
	}
    /**
     * @return MapList of Songs of this Album Instance
     */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	
}
