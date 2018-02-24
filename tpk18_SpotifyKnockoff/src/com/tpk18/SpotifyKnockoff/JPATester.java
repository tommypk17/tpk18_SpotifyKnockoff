package com.tpk18.SpotifyKnockoff;



public class JPATester {

	public static void main(String[] args) {
		SongController sc = new SongController();
		AlbumController ac = new AlbumController();
		ArtistController arc = new ArtistController();

		System.out.println("Testing operations of JPA");
		
		System.out.println("Creating a new Song");
		Song s1 = sc.createSong("a Song", 13, "", "02/21/2018", "02/21/2018");
		
		System.out.println("Creating a new Song to be deleted");
		Song s2 = sc.createSong("Song to be deleted", 3, "", "02/23/2018", "02/23/2018");
		
		System.out.println("2 new songs were created "+s1.getTitle()+" "+s2.getTitle());
		
		System.out.println("Updating Song: "+s1.getTitle());
		sc.updateSong(s1.getSongID(), "title", "I changed!");
		System.out.println("Song updated: "+s1.getTitle());
		
		System.out.println("Deleting Song: "+s2.getTitle());
		sc.deleteSong(s2.getSongID());
		System.out.println("Song deleted: "+s2.getTitle());
		
		

		System.out.println("Creating a new Album");
		Album a1 = ac.createAlbum("Bitter Strange", "05/08/2017", "", "Universal", 10, "PG-13", 35);
		
		System.out.println("Creating a new Album to be deleted");
		Album a2 = ac.createAlbum("The Afternoon", "04/21/2016", "", "Sony Records", 8, "PG-13", 40);
		
		System.out.println("2 new Albums were created "+a2.getTitle()+" "+a2.getTitle());
		
		System.out.println("Updating Album: "+a1.getTitle());
		ac.updateAlbum(a1.getAlbumID(), "title", "I changed!");
		System.out.println("Album updated: "+a1.getTitle());
		
		System.out.println("Deleting Album: "+a2.getTitle());
		ac.deleteAlbum(a2.getAlbumID());
		System.out.println("Album deleted: "+a2.getTitle());
		


		System.out.println("Creating a new Artist");
		Artist ar1 = arc.createArtist("Michael", "Jackson", "Jackson5", "here lies fluffy, she was loved");
		
		System.out.println("Creating a new Album to be deleted");
		Artist ar2 = arc.createArtist("Katy", "perry", "Katy Perry", "I'm like, really cool");
		
		System.out.println("2 new Artists were created "+ar1.getFirstName()+" "+ar1.getLastName()+" "+ar2.getFirstName()+" "+ar2.getLastName());
		
		System.out.println("Updating Artist: "+ar1.getFirstName()+" "+ar1.getLastName());
		arc.updateArtist(ar1.getArtistID(), "bio", "I changed my bio!");
		System.out.println("Artist updated: "+ar1.getFirstName()+" "+ar2.getLastName());
		
		System.out.println("Deleting Artist: "+ar2.getFirstName()+" "+ar2.getLastName());
		arc.deleteArtist(ar2.getArtistID());
		System.out.println("Artist deleted: "+ar2.getFirstName()+" "+ar2.getLastName());


	}

}
