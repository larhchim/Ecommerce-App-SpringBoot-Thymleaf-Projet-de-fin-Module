package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Categorie;

@Component
public class ICategorieImpl implements ICategorieDAO{
	
	@Autowired
	private EntityManager entity;

	@Override
	@Transactional
	public void ajouterCategorie(Categorie c) {
		
			

			entity.persist(c);
			
		
		
        
	}

	@Override
	@Transactional
	public List<Categorie> listeCategories() {
		
		Query catq =entity.createQuery("from Categorie");
		List<Categorie> ArrCat=catq.getResultList();
		
		return ArrCat;
	}
	

	@Override
	@Transactional
	public Categorie getCategorie(int idCat) {

		Categorie cat = entity.find(Categorie.class, idCat);
		
		return cat;
	}

	@Override
	@Transactional
	public void supprimerCategrorie(int idcat) {
		
		Categorie cat = entity.find(Categorie.class, idcat);
		entity.remove(cat);

	}

	@Override
	@Transactional
	public void  modifierCategorie(int c,Categorie r) {
	
		Categorie cat = entity.find(Categorie.class, c);
		cat.setDescription(r.getDescription());
		cat.setNomCategorie(r.getNomCategorie());
		cat.setPhoto(r.getPhoto());
		cat.setActived(r.isActived());
		entity.merge(cat);

	}
	

}
