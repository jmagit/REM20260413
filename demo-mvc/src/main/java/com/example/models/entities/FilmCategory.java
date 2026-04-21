package com.example.models.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDateTime;


/**
 * The persistent class for the FILM_CATEGORY database table.
 * 
 */
@Entity
@Table(name="FILM_CATEGORY")
@NamedQuery(name="FilmCategory.findAll", query="SELECT f FROM FilmCategory f")
public class FilmCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmCategoryPK id;

	@Column(name="LAST_UPDATE", insertable=false, updatable=false, nullable=false)
	private LocalDateTime lastUpdate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", insertable=false, updatable=false)
	private Category category;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="FILM_ID", insertable=false, updatable=false)
	private Film film;

	public FilmCategory() {
	}

	public FilmCategoryPK getId() {
		return this.id;
	}

	public void setId(FilmCategoryPK id) {
		this.id = id;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}