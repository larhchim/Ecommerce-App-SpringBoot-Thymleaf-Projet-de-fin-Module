package com.example.demo.metier;

import com.example.demo.entities.ComposantsPanier;
import com.example.demo.entities.Panier;

public interface IComposantsPanierMetier {
	

	public void AjouterComposantPanier(ComposantsPanier cP,Panier pn);
	
	public void SupprimerComposantPanier(int cP); 
		
	public void ModifierComposantPanier(ComposantsPanier cP,int id);
	
	public ComposantsPanier findComposant(int id);


}
