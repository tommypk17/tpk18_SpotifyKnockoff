package com.tpk18.SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlbumController {
	
	EntityManagerFactory albumFactory;
	EntityManager albumManager;
	
	public AlbumController(){
		albumFactory = Persistence.createEntityManagerFactory("tpk18_SpotifyKnockoff");
		albumManager = albumFactory.createEntityManager();
	}
	public Album createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		albumManager.getTransaction().begin();
		Album a = new Album();
		a.setAlbumID(UUID.randomUUID().toString());
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);
		albumManager.persist(a);
		albumManager.getTransaction().commit();
		return a;
	}
	public Album deleteAlbum(String albumID){
		albumManager.getTransaction().begin();
		Album a = albumManager.find(Album.class, albumID);
		albumManager.remove(a);
		albumManager.persist(a);
		albumManager.getTransaction().commit();
		return a;
	}
	public Album updateAlbum(String albumID, String parameterToChange, String param){
		albumManager.getTransaction().begin();
		Album a = albumManager.find(Album.class, albumID);		
		if(parameterToChange == "title"){
			a.setTitle(param);
		}else if(parameterToChange == "releaseDate"){
			a.setLength(Double.parseDouble(param));
		}else if(parameterToChange == "coverImagePath"){
			a.setCoverImagePath(param);
		}else if(parameterToChange == "recordingCompany"){
			a.setRecordingCompany(param);
		}else if(parameterToChange == "numberOfTracks"){
			a.setNumberOfTracks(Integer.parseInt(param));
		}else if(parameterToChange == "pmrcRating"){
			a.setPmrcRating(param);
		}else if(parameterToChange == "length"){
			a.setLength(Double.parseDouble(param));
		}
		
		albumManager.persist(a);
		albumManager.getTransaction().commit();
		return a;
	}
}
