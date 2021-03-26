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
public class ComposantsPanier implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="ID_PANIER")
	private Panier panier;
	
	@ManyToOne
	@JoinColumn(name="ID_PRODUIT")
	private Produit prod;
	
	
	@Column(name="QUANTITE_SELECTION")
	private int quantite;

	
	public ComposantsPanier(Panier panier, Produit produit, int quantite) {
		
		this.panier = panier;
		this.prod = produit;
		this.quantite = quantite;
		
	}
	
	

	public ComposantsPanier() {
	
		
	}



	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public Panier getPanier() {
		return panier;
	}


	public void setPanier(Panier panier) {
		this.panier = panier;
	}


	public Produit getProduit() {
		return prod;
	}


	public void setProduit(Produit produit) {
		this.prod = produit;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	@Override
	public String toString() {
		return "ComposantsPanier [panier=" + panier + ", produit=" + prod + ", quantite=" + quantite + "]";
	}
	
	
	
	
	
	

}
