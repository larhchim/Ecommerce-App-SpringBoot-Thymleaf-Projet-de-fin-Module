package com.example.demo.dao;

import com.example.demo.entities.Panier;

public interface IPanierDAO {
	
	public Panier FindPanier(int pn);

	public void Ajouterpanier(Panier pn);
	
	public void SupprimerPanier(int pn);
	
	public Panier LastPanier();

}
 