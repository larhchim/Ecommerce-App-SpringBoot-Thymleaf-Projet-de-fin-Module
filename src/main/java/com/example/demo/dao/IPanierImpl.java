package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.PanierRepository;
import com.example.demo.entities.Panier;

@Component
public class IPanierImpl implements IPanierDAO{
	
	@Autowired
	PanierRepository panRep;

	@Autowired
	private EntityManager entity;
	
	@Override
	@Transactional
	public Panier FindPanier(int pn) {
		
		Panier p=entity.find(Panier.class, pn);
		return p;
		
	}


	@Override
	@Transactional
	public void Ajouterpanier(Panier pn) {
		
		entity.persist(pn);
	}

	@Override
	@Transactional
	public void SupprimerPanier(int pn) {
		
		entity.remove(entity.find(Panier.class, pn));
		
	}


	@Override
	@Transactional
	public Panier LastPanier() {
		
		Panier pn=panRep.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);
		return pn;
	}

	

}
