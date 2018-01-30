package com.tpk18.SpotifyKnockoff;

import java.awt.image.DataBufferUShort;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class SpotifyTester {
	static Scanner siStrings = new Scanner(System.in);
	static Scanner siDoubles = new Scanner(System.in);
	static ArrayList<Song> songs;
	static ArrayList<Album> albums;
	static DbUtilities db = new DbUtilities();
	
	public static void main(String[] args) {
		buildSongObjects();
		buildAlbumObjects();
		
		boolean stop = false;
		while(!stop){
		System.out.println("Here are actions you can perform:");
		System.out.println("\tFind a song(1)");
		System.out.println("\tAdd a song(2)");
		System.out.println("\tDelete a song(3)");
		System.out.println("\tFind an Album(4)");
		System.out.println("\tAdd an Album(5)");
		System.out.println("\tDelete an Album(6)");
		System.out.println("Please enter an Integer from above:");
		int choice = (int) siDoubles.nextDouble();
		switch(choice){
			case 1:findSong();break;
			case 2:addSong();break;
			case 3:deleteSong();break;
			case 4:findAlbum();break;
			case 5:addAlbum();buildAlbumObjects();break;
			case 6:deleteAlbum();break;
		}
		System.out.println("Stop? y/n ");
		if(siStrings.nextLine().equalsIgnoreCase("y"))stop = true;
		buildAlbumObjects();buildSongObjects();
		}
		
	}
	private static void buildSongObjects(){
		String sql = "SELECT song_id FROM song;";
		songs = new ArrayList<Song>();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				songs.add(new Song(rs.getString("song_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void buildAlbumObjects(){
		String sql = "SELECT album_id FROM album;";
		albums = new ArrayList<Album>();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				albums.add(new Album(rs.getString("album_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void deleteAlbum() {
		Album album = findAlbum();
		System.out.println("Are you sure you want to delete this album? y/n: ");
		if(siStrings.nextLine().equalsIgnoreCase("y")){
			album.deleteAlbum(album.getAlbumID());
			System.out.println("album deleted!");
		}
		else{
			System.out.println("No albums were deleted");
		}
		System.out.println();
	}

	private static Album addAlbum() {
		// TODO Auto-generated method stub
		String albumID = "";
		String albumTitle, albumReleaseDate, coverImagePath, recordingCompanyName, pmrcRating;
		int numOfTracks;
		double albumLength;
		System.out.println("Add a new album");
		System.out.println("Title: ");
		albumTitle = siStrings.nextLine();
		System.out.println("Length: ");
		while(true){
			try{
				albumLength = siDoubles.nextDouble();
				break;
			}catch(InputMismatchException e){
				System.out.println("enter a double");
			}
		}
		System.out.println("Release Date: ");
		albumReleaseDate = siStrings.nextLine();
		System.out.println("Image File Path: ");
		coverImagePath= siStrings.nextLine();
		System.out.println("Record company name: ");
		recordingCompanyName = siStrings.nextLine();
		System.out.println("PMRC Rating: ");
		pmrcRating = siStrings.nextLine();
		System.out.println("Number of tracks: ");
		numOfTracks = siStrings.nextInt();
		Album album = new Album(albumTitle, albumReleaseDate, recordingCompanyName, numOfTracks, pmrcRating, albumLength);
		albumID = album.getAlbumID();
		return album;
	}

	private static Album findAlbum() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the ID of the Album you are looking for, or n to cancel");
		System.out.println("Here are the albums:");
		for(Album album: albums){
			System.out.println("\t"+album.getTitle()+" \n\t-> "+album.getAlbumID()+"\n");
		}
		Album foundAlbum = new Album(siStrings.nextLine());
		System.out.println();
		if(foundAlbum.getAlbumID() != null){
			Album album = new Album(foundAlbum.getAlbumID());
			System.out.println("Found Album!\nTitle: "+foundAlbum.getTitle());
			System.out.println("Length: "+foundAlbum.getLength());
			System.out.println("Release Date: "+foundAlbum.getReleaseDate());
			System.out.println("Release Date: "+foundAlbum.getReleaseDate());
			System.out.println("File Path: "+foundAlbum.getCoverImagePath());
		}else{
			System.out.println("Couldnt find album!");
		}
		System.out.println();
		return foundAlbum;

	}

	private static void deleteSong() {
		Song song = findSong();
		System.out.println("Are you sure you want to delete this song? y/n: ");
		if(siStrings.nextLine().equalsIgnoreCase("y")){
			song.deleteSong(song.getSongID());
			System.out.println("song deleted!");
		}
		else{
			System.out.println("No songs were deleted");
		}
		System.out.println();
	}

	private static void addSong() {
		// TODO Auto-generated method stub
		String title, filePath, recordDate, releaseDate;
		double length;
		System.out.println("\nAdd a new Song");
		System.out.println("Title: ");
		title = siStrings.nextLine();
		System.out.println("Length: ");
		while(true){
			try{
				length = siDoubles.nextDouble();
				break;
			}catch(InputMismatchException e){
				System.out.println("enter a double");
			}
		}
		System.out.println("Release Date: ");
		releaseDate = siStrings.nextLine();
		System.out.println("File Path: ");
		filePath = siStrings.nextLine();
		System.out.println("Record date: ");
		recordDate = siStrings.nextLine();
		System.out.println("Create a new album? y/n ");
		Album album = null;
		Song song = null;
		if(siStrings.nextLine().equalsIgnoreCase("y")){album = addAlbum();}
		else{
			System.out.println("Please enter the ID of the existing Album");
			System.out.println("Here are the Albums:");
			for(Album lookUpAlbum: albums){
				System.out.println("\t"+lookUpAlbum.getAlbumID()+" -> "+lookUpAlbum.getTitle());
			}
			Album foundAlbum = new Album(siStrings.nextLine());
			System.out.println();
			if(foundAlbum.getAlbumID() != null){
				album = foundAlbum;
			}
		}
		System.out.println("Creating new DB Record");
		song = new Song(title, length, releaseDate, recordDate, album.getAlbumID());
		System.out.println("Song Created!");
		song = new Song(song.getSongID());
		System.out.println("\tName: "+ song.getTitle());
		System.out.println("\tLength: "+ song.getLength());
		System.out.println("\tAlbum Name: "+ new Album(song.getAlbumID()).getTitle());
	}

	private static Song findSong() {
		System.out.println("Please enter the ID of the song you are looking for");
		System.out.println("Here are the songs:");
		for(Song song: songs){
			System.out.println("\t"+song.getTitle()+"\n\t-> "+song.getSongID()+"\n");
		}
		Song foundSong = new Song(siStrings.nextLine());
		System.out.println();
		if(foundSong.getSongID() != null){
			Album album = new Album(foundSong.getAlbumID());
			System.out.println("Found Song!\nTitle: "+foundSong.getTitle());
			System.out.println("Length: "+foundSong.getLength());
			System.out.println("Release Date: "+foundSong.getReleaseDate());
			System.out.println("Release Date: "+foundSong.getReleaseDate());
			System.out.println("File Path: "+foundSong.getFilePath());
			System.out.println("Album Name: "+album.getTitle());
		}else{
			System.out.println("Couldnt find song!");
		}
		System.out.println();
		return foundSong;
		
	}

}
