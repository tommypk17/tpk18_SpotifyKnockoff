package com.tpk18.SpotifyKnockoff;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DBSearch {
	static DbUtilities db = new DbUtilities();
	
	private static Artist findArtist(String searchTerm) {
		Artist foundArtist = new Artist(searchTerm);
		return foundArtist;
	}
	
	private static void addArtist(String artistFirstName, String artistLastName, String artistBio, String artistBandName) {
		Artist artist = new Artist(artistFirstName, artistLastName, artistBandName);
		if(!artistBio.isEmpty()){
			artist.setBio(artistBio);
		}
		
	}
	
	private static void deleteArtist() {

	}
	
	private static void deleteAlbum() {

	}

	private static void addAlbum(String albumTitle, String albumReleaseDate, String recordingCompanyName, String pmrcRating , int numOfTracks, double albumLength) {
		Album album = new Album(albumTitle, albumReleaseDate, recordingCompanyName, numOfTracks, pmrcRating, albumLength);
	}

	private static Album findAlbum(String searchTerm) {
		Album foundAlbum = new Album(searchTerm);
		return foundAlbum;

	}

	private static void deleteSong() {

	}

	private static void addSong(String title, String recordDate, String releaseDate, String albumID, double length) {
		Song song = new Song(title, length, releaseDate, recordDate);
		Album album = new Album(albumID);
		album.addSong(song);
		
	}

	private static Song findSong(String searchTerm) {
		Song foundSong = new Song(searchTerm);
		return foundSong;
		
	}

}
