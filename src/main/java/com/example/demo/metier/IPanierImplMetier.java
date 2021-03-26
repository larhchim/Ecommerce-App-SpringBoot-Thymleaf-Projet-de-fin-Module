package com.example.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IPanierDAO;
import com.example.demo.entities.Panier;

@Service
public class IPanierImplMetier implements IPanierMetier{

	@Autowired
	IPanierDAO pan;
	
	public void setPan(IPanierDAO pan) {
		this.pan = pan;
	}
	
	@Override
	public Panier FindPanier(int pn) {
		return pan.FindPanier(pn);
	}

	@Override
	public void Ajouterpanier(Panier pn) {
		
		pan.Ajouterpanier(pn);
		
	}

	@Override
	public void SupprimerPanier(int pn) {
		pan.SupprimerPanier(pn);
	}

	@Override
	public Panier LastPanier() {
		return pan.LastPanier();
	}

}
