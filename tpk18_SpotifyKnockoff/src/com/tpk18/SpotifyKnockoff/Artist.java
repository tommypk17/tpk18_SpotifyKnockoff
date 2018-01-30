package com.tpk18.SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Artist {
	private String artistID;
	private String firstName; 
	private String lastName;
	private String bandName;
	private String bio;
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
			e.printStackTrace();
		}
	}
    /**
     * Alternate Constructor - creates a new Artist, used to pull data from DB and sets instance variables with that data.
     * @param artistID - string value of the artist id
     */
	public Artist(String artistID) {
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
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
		this.artistID = null;
		this.firstName = null;
		this.lastName = null;
		this.bandName = null;
		this.bio = null;
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
		//delete song object
		destroyObject();
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

}
