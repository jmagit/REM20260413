package com.example.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;


/**
 * The persistent class for the ACTOR database table.
 * 
 */
@Entity
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTOR_ID")
	private long actorId;

	@Column(name="FIRST_NAME")
	@NotNull
	@Size(min=2, max=45)
	private String firstName;

	@Column(name="LAST_NAME")
	@NotNull
	@Size(min=2, max=45)
	private String lastName;

	@Column(name="LAST_UPDATE", insertable=false, updatable=false, nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime lastUpdate;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="actor")
	@JsonIgnore
	private List<FilmActor> filmActors;

	public Actor() {
	}

	public long getActorId() {
		return this.actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setActor(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setActor(null);

		return filmActor;
	}

}