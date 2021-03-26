package com.example.demo.metier;

import com.example.demo.entities.Panier;

public interface IPanierMetier {
	
	public Panier FindPanier(int pn);

	public void Ajouterpanier(Panier pn);
	
	public void SupprimerPanier(int pn);
	
	public Panier LastPanier();


}
