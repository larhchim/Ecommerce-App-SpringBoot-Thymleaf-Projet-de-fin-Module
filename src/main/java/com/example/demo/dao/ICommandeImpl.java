package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.demo.entities.*;
import com.example.demo.metier.ICommandeMetier;
import com.example.demo.metier.IComposantsPanierImplMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ICommandeImpl implements ICommandeDAO{

	@Autowired
	private EntityManager entity;
	@Autowired
	IComposantsPanierImplMetier iComposantsPanierImplMetier;
	@Override
	@Transactional
	public void AnnulerCommande(int c) {
		
		Commande com =entity.find(Commande.class, c);
		
		entity.remove(com);

	}

	

	@Override
	@Transactional
	public Commande ChargerCommande(int id) {
		
		Commande cmd = entity.find(Commande.class, id);
		return cmd;
		
	}

	@Override
	@Transactional
	public void AddComposantetoCommande(Composante c, Commande cmd) {
		
		Composante comp = new Composante();
		comp.setCommande(cmd);
		comp.setProduit(c.getProduit());
		comp.setQuantite(c.getQuantite());
		entity.persist(comp);
		
	}

	@Override
	@Transactional
	public int ValiderCommande(int idPanier, User c, boolean e, String local) {
		
		
		Commande cmd = new Commande();
		double random = Math.random() * 99999999;
		if(random<1000){
			random = random + 10000000;
		}
		int id = (int)random;
		cmd.setIdCommande(id);
		cmd.setEtat(e);
		cmd.setLocalisation(local);
		cmd.setUser(c);
		cmd.setDateCommande(new Date());
		entity.detach(cmd);
		entity.persist(cmd);
		
		List<ComposantsPanier> listeCmpP =entity.find(Panier.class, idPanier).getComponents();
	   
		for (ComposantsPanier cP : listeCmpP) {
			
			Composante ct = new Composante();
			ct.setCommande(cmd);
			ct.setProduit(cP.getProduit());
			ct.setQuantite(cP.getQuantite());
			Produit p = cP.getProduit();
			p.setQuantite(p.getQuantite() - cP.getQuantite());
			entity.persist(p);
			entity.persist(ct);
			//AddComposantetoCommande(ct, cmd);
		}

		for (ComposantsPanier cP : listeCmpP) {
			iComposantsPanierImplMetier.SupprimerComposantPanier(cP.getId());
		}
		return id;
	}



	@Override
	@Transactional
	public void ModifierCommande(int id, String local) {
		
		Commande com =entity.find(Commande.class, id);
		
		com.setLocalisation(local);
		
		entity.persist(com);
		
	}

	

}
