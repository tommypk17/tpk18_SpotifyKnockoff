package com.tpk18.SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongController {
	EntityManagerFactory songFactory;
	EntityManager songManager;
	
	public SongController(){
		songFactory = Persistence.createEntityManagerFactory("tpk18_SpotifyKnockoff");
		songManager = songFactory.createEntityManager();
	}
	public void createSong(String title, double length, String filePath, String releaseDate, String recordDate){
		songManager.getTransaction().begin();
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		s.setFilePath(filePath);
		songManager.persist(s);
		songManager.getTransaction().commit();
	}
	public void deleteSong(String songID){
		songManager.getTransaction().begin();
		Song s = songManager.find(Song.class, songID);
		songManager.remove(s);
		songManager.persist(s);
		songManager.getTransaction().commit();
	}
	public void updateSong(String songID, String parameterToChange, String param){
		songManager.getTransaction().begin();
		Song s = songManager.find(Song.class, songID);
		if(parameterToChange == "title"){
			s.setTitle(param);
		}else if(parameterToChange == "length"){
			s.setLength(Double.parseDouble(param));
		}else if(parameterToChange == "filePath"){
			s.setFilePath(param);
		}else if(parameterToChange == "releaseDate"){
			s.setReleaseDate(param);
		}else if(parameterToChange == "recordDate"){
			s.setRecordDate(param);
		}
		songManager.persist(s);
		songManager.getTransaction().commit();
	}
	
	
	
}
