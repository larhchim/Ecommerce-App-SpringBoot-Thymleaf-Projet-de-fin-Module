package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Categorie;
import com.example.demo.entities.Produit;

@Component
public class IProduitImpl implements IProduitDAO{
	
	@Autowired
	EntityManager entity;

	@Override
	@Transactional
	public void ajouterProduit(Produit p, int i) {
		
		Produit prod = new Produit();
		prod.setDescription(p.getDescription());
		prod.setDesignation(p.getDesignation());
		prod.setPhoto(p.getPhoto());
		prod.setPrix(p.getPrix());
		prod.setQuantite(p.getQuantite());
		prod.setUrlphoto(p.getUrlphoto());
		prod.setCategorie(entity.find(Categorie.class, i));
		
		entity.persist(prod);
		

	}

	@Override
	@Transactional
	public List<Produit> listeproduits() {
		
		List<Produit> lsp =entity.createQuery("FROM Produit").getResultList();
		
		return lsp;
	}

	@Override
	@Transactional
	public List<Produit> produitsParMotCle(String mc) {
		
		String a=mc.toLowerCase();
		List<Produit>  liste = new ArrayList<Produit>();
		for (Produit produit : listeproduits()) {
			
			if(produit.getDesignation().toLowerCase().indexOf(a)>=0) {
				liste.add(produit);
			}
			
		}
	
		return liste;
	}

	@Override
	@Transactional
	public List<Produit> produitsParCategorie(int idCat) {
		
		Categorie cat =entity.find(Categorie.class, idCat);
		//System.out.println("************");
		//System.out.println(cat);
	//	System.out.println("************");

		List<Produit> lsp = new ArrayList<>();
		lsp=cat.getProduits();
		System.out.println(lsp.toString());

		return lsp;
	}

	@Override
	@Transactional
	public Produit getProduit(int idP) {

		Produit prd =entity.find(Produit.class, idP);
		return prd;
	}

	@Override
	@Transactional
	public void supprimerProduit(int idP) {
		
		Produit prd = entity.find(Produit.class, idP);
		prd.setQuantite(0);
		entity.persist(prd);

	}

	@Override
	@Transactional
	public void modifierProduit(Produit p,int id) {
		
		Produit prd =entity.find(Produit.class, id);
		prd.setCategorie(p.getCategorie());
		prd.setDescription(p.getDescription());
		prd.setDesignation(p.getDesignation());
		prd.setPhoto(p.getPhoto());
		prd.setPrix(p.getPrix());
		prd.setQuantite(p.getQuantite());
		prd.setUrlphoto(p.getUrlphoto());
		entity.persist(prd);
		

	}

}
