package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_Produit")
	private int idProduit;
	
	private String designation;
	private String description;
	private double prix;
	
	@Column(name="QUANTITE_STOCK")
	private int quantite;
	
	@Column(name="URL")
	private String urlphoto;
	
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIE")
	private Categorie categorie;
	
	@OneToMany(mappedBy="produit")
	private List<Composante> composantes;
	
	@OneToMany(mappedBy="prod")
	private List<ComposantsPanier> listecompPan;
	
	public Produit(String designation, String description, double prix, int quantite, String photo) {
		
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.urlphoto = photo;
		
	}

	
	public Produit() {
		
	}


	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getPhoto() {
		return urlphoto;
	}
	public void setPhoto(String photo) {
		this.urlphoto = photo;
	}
	

	public String getUrlphoto() {
		return urlphoto;
	}


	public void setUrlphoto(String urlphoto) {
		this.urlphoto = urlphoto;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<Composante> getComposantes() {
		return composantes;
	}


	public void setComposantes(List<Composante> composantes) {
		this.composantes = composantes;
	}


	public List<ComposantsPanier> getListecompPan() {
		return listecompPan;
	}


	public void setListecompPan(List<ComposantsPanier> listecompPan) {
		this.listecompPan = listecompPan;
	}


	@Override
	public String toString() {
		return "Produit [designation=" + designation + ", description=" + description + ", prix=" + prix + ", quantite="
				+ quantite + ", photo=" + urlphoto  + "]"+"\n";
	}
	
	

}
