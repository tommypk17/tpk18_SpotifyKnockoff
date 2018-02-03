package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	private Map<String, Song> albumSongs = new HashMap<String, Song>();
	
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
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
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
			e.printStackTrace();
		}
	}
	/**
	 * Alternate Constructor - creates a new Album, used to pull data from DB and sets instance variables with that data.
	 * @param albumID - string value of the album id
	 */
	public Album(String albumID) {
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
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
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

    /**
     * This method is used to remove a specific Album based on the albumID as a String
     * @param albumID - album id as String
     */
	public void deleteAlbum(String albumID){
		String sql = "DELETE FROM album WHERE album_id='"+albumID+"';";
		Iterator<Entry<String, Song>> it = albumSongs.entrySet().iterator();
		while(it.hasNext()){
			Song song = (Song) it.next().getValue();
			song.deleteSong(song.getSongID());
		}
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}
    /**
     * This method is used to add a specific song to the Album list
     * @param song - song object
     */
	public void addSong(Song song) {
		try{
		this.albumSongs.put(song.getSongID(), song);
		DbUtilities db = new DbUtilities();
		if(this.getAlbumID() != null){
			String sql = "INSERT INTO album_song(fk_album_id, fk_song_id) VALUES('"+this.getAlbumID()+"' ,'"+song.getSongID()+"');";
			db.executeQuery(sql);
			db.closeDbConnection();
			System.out.println("Succesfully added song-album relationship");
		}
		}catch(Exception e){
			System.out.println("An error has occured when trying to add song-album realtionship!");
		}
	}
    /**
     * This method is used to remove a specific song from the Album list
     * @param song - song object
     */
	public void deleteSong(Song song) {
		this.albumSongs.remove(song).getSongID();
	}
    /**
     * This method is used to remove a specific song from the Album list
     * @param songID - song id as String
     */
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}	
    /**
     * @return ID of this Album Instance
     */
	public String getAlbumID() {
		return albumID;
	}
    /**
     * @return Title of this Album Instance
     */
	public String getTitle() {
		return title;
	}
    /**
     * @return Release Date of this Album Instance
     */
	public String getReleaseDate() {
		return releaseDate;
	}
    /**
     * @return Cover Image Path of this Album Instance
     */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	
    public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	/**
     * @return Recording Company Name of this Album Instance
     */
	public String getRecordingCompany() {
		return recordingCompany;
	}
    /**
     * @return Number of Tracks for this Album Instance
     */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
    /**
     * @return PMRC Rating of this Album Instance
     */
	public String getPmrcRating() {
		return pmrcRating;
	}
    /**
     * @return Length of time of this Album Instance
     */
	public double getLength() {
		return length;
	}
    /**
     * @return MapList of Songs of this Album Instance
     */
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	
}
