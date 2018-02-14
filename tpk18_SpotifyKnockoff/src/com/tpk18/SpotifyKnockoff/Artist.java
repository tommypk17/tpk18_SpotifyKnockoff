package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
public class Artist {
	private String artistID;
	private String firstName; 
	private String lastName;
	private String bandName;
	private String bio;
	
	private Map<String, Song> artistSongs = new HashMap<>();
    /**
     * Constructor - creates a new Artist with parameters, used to add new Artist to database.
     * @param firstName - string value of the artist's first name
     * @param lastName - string value of the artist's last name
     * @param bandName - string value of the artist's band name
     */
	public Artist(String firstName, String lastName, String bandName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = UUID.randomUUID().toString();
		
		String sql = "INSERT INTO artist (artist_id,first_name,last_name,band_name) ";
		sql += "VALUES (?, ?, ?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.artistID);
			ps.setString(2,  this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}
	}
    /**
     * Alternate Constructor - creates a new Artist, used to pull data from DB and sets instance variables with that data.
     * @param artistID - string value of the artist id
     */
	public Artist(String artistID) {
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db;
		ResultSet rs;
		try {
			db = new DbUtilities();
			rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
			sql = "SELECT * FROM song LEFT JOIN song_artist ON song.song_id = song_artist.fk_song_id WHERE song_artist.fk_artist_id='"+ artistID + "';";
			rs = db.getResultSet(sql);
			while(rs.next()){
				Song song = new Song(rs.getString("song_id"), rs.getString("title"), rs.getDouble("length"), rs.getString( "release_date"), rs.getString( "record_date"));
				song.setFilePath(rs.getString("file_path"));
				this.artistSongs.put(song.getSongID(), song);
				song = null;
			}
			db.closeDbConnection();
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}finally{
			db = null;
			rs = null;
		}
		
	}
    /**
     * Alternative Constructor - creates a new Artist with parameters, used to add new Artist to database.
     * @param artistID -  string value of the artist's id
     * @param firstName - string value of the artist's first name
     * @param lastName - string value of the artist's last name
     * @param bandName - string value of the artist's band name
     */
	public Artist(String artistId, String firstName, String lastName, String bandName) {
		this.artistID = artistId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
	}
    /**
     * This method is used to remove a specific Artist based on the artistID as a String
     * @param artistID - artist id as String
     */
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM artist WHERE artist_id='"+artistID+"';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}
	
	
    /**
     * @return ID of this Artist Instance
     */
	public String getArtistID() {
		return artistID;
	}
    /**
     * @return First Name of this Artist Instance
     */
	public String getFirstName() {
		return firstName;
	}
    /**
     * @return Last Name of this Artist Instance
     */
	public String getLastName() {
		return lastName;
	}
    /**
     * @return Band Name of this Artist Instance
     */
	public String getBandName() {
		return bandName;
	}
    /**
     * @return Biography of this Artist Instance
     */
	public String getBio() {
		return bio;
	}
    /**
     * @return MapList of Songs for Artist Instance
     */
	public Map<String, Song> getArtistSongs() {
		return artistSongs;
	}
	/**
	 * 
	 * @return Object[] - returns Object[] containing Song instance variables
	 */
	public Object[] toArray(){
		return new Object[] {this.artistID, this.firstName, this.lastName, this.bandName, this.bio};
	}
	public void setBio(String bio) {
		this.bio = bio;
		String sql = "UPDATE artist SET bio = '"+bio+"' WHERE artist_id = '"+this.artistID+"';";
		try {
			DbUtilities db = new DbUtilities();
			db.executeQuery(sql);
			db.closeDbConnection();
			db = null;
		} catch (Exception e) {
			ErrorLogger.log(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
