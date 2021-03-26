package com.example.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IComposantsPanierDAO;
import com.example.demo.entities.ComposantsPanier;
import com.example.demo.entities.Panier;

@Service
public class IComposantsPanierImplMetier implements IComposantsPanierMetier{
	
	@Autowired
	IComposantsPanierDAO cpa;
	
	public void setCpa(IComposantsPanierDAO cpa) {
		this.cpa = cpa;
	}

	@Override
	public void AjouterComposantPanier(ComposantsPanier cP, Panier pn) {
		
		cpa.AjouterComposantPanier(cP, pn);
		
	}

	@Override
	public void SupprimerComposantPanier(int cP) {
		
		cpa.SupprimerComposantPanier(cP);
		
	}

	@Override
	public void ModifierComposantPanier(ComposantsPanier cP, int id) {
		
		cpa.ModifierComposantPanier(cP, id);
		
	}

	@Override
	public ComposantsPanier findComposant(int id) {
		return cpa.findComposant(id);
	}

}
