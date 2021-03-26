package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Composante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMPOSANTE")
	private int idComposante;
	
	@Column(name="QUANTITE_COMMANDE")
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name="ID_Produit")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="ID_COMMANDE")
	private Commande commande;
	
	
	public Composante(int quantite, Produit produit) {
		super();
		this.quantite = quantite;
		this.produit = produit;
	}
	
	
	public Composante() {
		
	}


	public int getIdComposante() {
		return idComposante;
	}
	public void setIdComposante(int idComposante) {
		this.idComposante = idComposante;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	@Override
	public String toString() {
		return "Composante [quantite=" + quantite + ", produit=" + produit + "]"+"\n";
	}

	
}
