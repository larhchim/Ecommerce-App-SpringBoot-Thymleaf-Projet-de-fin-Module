package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Panier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PANIER")
	private int id;
	
	private Date dateAjout;
	
	@OneToMany(mappedBy="panier")
	private List<ComposantsPanier> components ;
	
	   @OneToOne(mappedBy = "panier")
	    private User user;

	public Panier(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Panier() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public List<ComposantsPanier> getComponents() {
		return components;
	}

	public void setComponents(List<ComposantsPanier> components) {
		this.components = components;
	}

	@Override
	public String toString() {
		return "Panier [dateAjout=" + dateAjout + "]";
	}

	
	
	
	
	 
	
	
	
	
	
}