package com.example.demo.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICategorieDAO;
import com.example.demo.entities.Categorie;

@Service
public class ICategorieImplMetier implements ICategorieMetier{
	
	@Autowired
	ICategorieDAO cat;
	
	public void setCat(ICategorieDAO cat) {
		this.cat = cat;
	}

	@Override
	public void ajouterCategorie(Categorie c) {
		
		cat.ajouterCategorie(c);
		
	}

	@Override
	public List<Categorie> listeCategories() {
		
		return cat.listeCategories();
	}

	@Override
	public Categorie getCategorie(int idCat) {
		
		return cat.getCategorie(idCat);
	}

	@Override
	public void supprimerCategrorie(int idcat) {
		
		cat.supprimerCategrorie(idcat);
		
	}

	@Override
	public void modifierCategorie(int c, Categorie r) {
		
		cat.modifierCategorie(c, r);
		
	} 

}
