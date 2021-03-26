package com.example.demo.metier;

import java.util.List;

import com.example.demo.entities.Categorie;

public interface ICategorieMetier {
	
	public void ajouterCategorie(Categorie c);

	public List<Categorie> listeCategories();

	public Categorie getCategorie(int idCat);
	
	public void supprimerCategrorie(int idcat);
	
	public void modifierCategorie(int c,Categorie r);

}
