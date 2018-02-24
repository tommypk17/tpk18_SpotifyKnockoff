package com.tpk18.SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistController {
	
	EntityManagerFactory artistFactory;
	EntityManager artistManager;
	
	public ArtistController(){
		artistFactory = Persistence.createEntityManagerFactory("tpk18_SpotifyKnockoff");
		artistManager = artistFactory.createEntityManager();
	}
	public Artist createArtist(String firstName, String lastName, String bandName, String bio){
		artistManager.getTransaction().begin();
		Artist a = new Artist();
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);

		artistManager.persist(a);
		artistManager.getTransaction().commit();
		return a;
	}
	public Artist deleteArtist(String artistID){
		artistManager.getTransaction().begin();
		Artist a = artistManager.find(Artist.class, artistID);
		artistManager.remove(a);
		artistManager.persist(a);
		artistManager.getTransaction().commit();
		return a;
	}
	public Artist updateArtist(String artistID, String parameterToChange, String param){
		artistManager.getTransaction().begin();
		Artist a = artistManager.find(Artist.class, artistID);		
		if(parameterToChange == "firstName"){
			a.setFirstName(param);
		}else if(parameterToChange == "lastName"){
			a.setLastName(param);
		}else if(parameterToChange == "bandName"){
			a.setBandName(param);
		}else if(parameterToChange == "bio"){
			a.setBio(param);
		}
		
		artistManager.persist(a);
		artistManager.getTransaction().commit();
		return a;
	}
}
