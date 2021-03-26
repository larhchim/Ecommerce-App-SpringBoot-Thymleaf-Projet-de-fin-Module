package com.example.demo.dao;

import com.example.demo.entities.Composante;
import com.example.demo.entities.Produit;

public interface IComposanteDAO {
	
	public void supprimerComposante(Composante comp);
	
	public void ajouterComposante(Composante comp);
	
	public void ModifierComposante(Composante comp,Long id);
	
	public Composante LaComposante(Long comp);

	public void incrementer(Produit p);
	
	public void decrementer(Produit p);
}
