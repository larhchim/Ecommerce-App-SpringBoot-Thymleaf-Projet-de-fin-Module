package com.example.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Composante;
import com.example.demo.entities.Produit;

@Component
public class IComposanteImpl implements IComposanteDAO{
	
	@Autowired
	private EntityManager entity;

	@Override
	@Transactional
	public void supprimerComposante(Composante comp) {
		
		entity.remove(comp);
		
	}

	@Override
	@Transactional
	public void ajouterComposante(Composante comp) {
		
		Composante c = new Composante();
		c.setCommande(comp.getCommande());
		c.setProduit(comp.getProduit());
		c.setQuantite(comp.getQuantite());
		
		entity.persist(c);

	}

	@Override
	@Transactional
	public void ModifierComposante(Composante comp,Long id) {
		
		Composante compo = entity.find(Composante.class,id);
		compo=comp;
		entity.persist(compo);

	}

	@Override
	@Transactional
	public Composante LaComposante(Long id) {

		Composante comp =entity.find(Composante.class, id);
		return comp;
		
	}

	@Override
	@Transactional
	public void incrementer(Produit p) {
		
		p.setQuantite(p.getQuantite()+1);
		entity.persist(p);
		
	}

	@Override
	@Transactional
	public void decrementer(Produit p) {
		
		p.setQuantite(p.getQuantite()-1);
		entity.persist(p);
				
	}



}
