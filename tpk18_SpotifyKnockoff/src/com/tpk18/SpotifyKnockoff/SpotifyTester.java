package com.tpk18.SpotifyKnockoff;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
public class SpotifyTester {
	static Scanner siStrings = new Scanner(System.in);
	static Scanner siDoubles = new Scanner(System.in);
	static DbUtilities db = new DbUtilities();
	
	public static void main(String[] args) {

		
		boolean stop = false;
		while(!stop){
		System.out.println("Here are actions you can perform:\n");
		System.out.println("\tFind a song(1)");
		System.out.println("\tAdd a song(2)");
		System.out.println("\tDelete a song(3)");
		System.out.println("\tFind an Album(4)");
		System.out.println("\tAdd an Album(5)");
		System.out.println("\tDelete an Album(6)");
		System.out.println("\tFind an Artist(7)");
		System.out.println("\tAdd an Artist(8)");
		System.out.println("\tDelete an Artist(9)\n");
		System.out.println("Please enter an Integer from above:");
		int choice = (int) siDoubles.nextDouble();
		switch(choice){
			case 1:findSong();break;
			case 2:addSong();break;
			case 3:deleteSong();break;
			case 4:findAlbum();break;
			case 5:addAlbum();break;
			case 6:deleteAlbum();break;
			case 7:findArtist();break;
			case 8:addArtist();break;
			case 9:deleteArtist();break;

		}
		System.out.println("Stop? y/n ");
		if(siStrings.nextLine().equalsIgnoreCase("y"))stop = true;

		}
		
	}
	private static Artist findArtist() {
		System.out.println("Please enter the ID of the Artist you are looking for, or n to cancel");
		Artist foundArtist = new Artist(siStrings.nextLine());
		System.out.println();
		if(foundArtist.getArtistID() != null){
			System.out.println("Found Artist!\nTitle: "+foundArtist.getFirstName());
			System.out.println("Last Name: "+foundArtist.getLastName());
			System.out.println("Band: "+foundArtist.getBandName());
			System.out.println("Bio: "+foundArtist.getBio());
		}else{
			System.out.println("Couldnt find Artist!");
		}
		System.out.println();
		return foundArtist;
	}
	private static void addArtist() {
		String artistFirstName, artistLastName, artistBio, artistBandName;
		System.out.println("Artist First Name: ");
		artistFirstName = siStrings.nextLine();
		System.out.println("Artist Last Name: ");
		artistLastName= siStrings.nextLine();
		System.out.println("Bio: ");
		artistBio = siStrings.nextLine();
		System.out.println("Artist Band Name: ");
		artistBandName = siStrings.nextLine();
		Artist artist = new Artist(artistFirstName, artistLastName, artistBandName);
		if(!artistBio.isEmpty()){
			artist.setBio(artistBio);
		}
		
	}
	private static void deleteArtist() {
		Artist artist = findArtist();
		System.out.println("Are you sure you want to delete this artist? y/n: ");
		if(siStrings.nextLine().equalsIgnoreCase("y")){
			artist.deleteArtist(artist.getArtistID());
			System.out.println("artist deleted!");
		}
		else{
			System.out.println("No artists were deleted");
		}
		System.out.println();
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
		String albumTitle, albumReleaseDate, recordingCompanyName, pmrcRating;
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
		System.out.println("Record company name: ");
		recordingCompanyName = siStrings.nextLine();
		System.out.println("PMRC Rating: ");
		pmrcRating = siStrings.nextLine();
		System.out.println("Number of tracks: ");
		numOfTracks = siStrings.nextInt();
		Album album = new Album(albumTitle, albumReleaseDate, recordingCompanyName, numOfTracks, pmrcRating, albumLength);
		return album;
	}

	private static Album findAlbum() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the ID of the Album you are looking for, or n to cancel");
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
		String title, recordDate, releaseDate, albumID;
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
		System.out.println("Record date: ");
		recordDate = siStrings.nextLine();
		
		Song song = null;
		System.out.println("Creating new DB Record");
		song = new Song(title, length, releaseDate, recordDate);
		System.out.println("Song Created!");
		System.out.println("Would you like to link this to an album? y/n");
		if(siStrings.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Please enter the Album ID: ");
			albumID = siStrings.nextLine();
			Album album = new Album(albumID);
			album.addSong(song);
		}
	}

	private static Song findSong() {
		System.out.println("Please enter the ID of the song you are looking for");
		Song foundSong = new Song(siStrings.nextLine());
		System.out.println();
		if(foundSong.getSongID() != null){
			System.out.println("Found Song!\nTitle: "+foundSong.getTitle());
			System.out.println("Length: "+foundSong.getLength());
			System.out.println("Release Date: "+foundSong.getReleaseDate());
			System.out.println("Release Date: "+foundSong.getReleaseDate());
			System.out.println("File Path: "+foundSong.getFilePath());
		}else{
			System.out.println("Couldnt find song!");
		}
		System.out.println();
		return foundSong;
		
	}

}
