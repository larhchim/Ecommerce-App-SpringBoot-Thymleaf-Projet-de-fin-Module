package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ComposantsPanier;
import com.example.demo.entities.Panier;

@Component
public class IComposantsPanierImpl implements IComposantsPanierDAO{
	
	@Autowired
	private EntityManager entity;

	@Override
	@Transactional
	public void AjouterComposantPanier(ComposantsPanier cP,Panier pn) {
		
		cP.setPanier(pn);
		entity.persist(cP); 
		
	

	}

	@Override
	@Transactional
	public void SupprimerComposantPanier(int cP) {
		
		ComposantsPanier c =entity.find(ComposantsPanier.class, cP);
		
		entity.remove(c);



	}


	@Override
	@Transactional
	public void ModifierComposantPanier(ComposantsPanier cP,int id) {
		
		ComposantsPanier cmp =entity.find(ComposantsPanier.class, id);

		cmp.setPanier(cP.getPanier());
		cmp.setProduit(cP.getProduit());
		cmp.setQuantite(cP.getQuantite());
	
		
		entity.persist(cmp);
		
	}

	@Override
	public ComposantsPanier findComposant(int id) {
		ComposantsPanier comp=entity.find(ComposantsPanier.class, id);
		return comp;
	}
	
	

}
