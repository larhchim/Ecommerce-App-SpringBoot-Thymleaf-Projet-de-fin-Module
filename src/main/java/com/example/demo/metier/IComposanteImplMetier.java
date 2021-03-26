package com.example.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IComposanteDAO;
import com.example.demo.dao.IProduitDAO;
import com.example.demo.entities.Composante;
import com.example.demo.entities.Produit;

@Service
public class IComposanteImplMetier implements IComposanteMetier{
	
	@Autowired 
	IComposanteDAO composante;
	
	@Autowired
	IProduitDAO prod;
	
	public void setComp(IComposanteDAO comp) {
		
		this.composante = comp;
		
	}

	@Override
	public void supprimerComposante(Composante comp) {
      
		composante.supprimerComposante(comp);
		
		composante.incrementer(comp.getProduit());
		
	}

	@Override
	public void ajouterComposante(Composante comp) {
		
		composante.ajouterComposante(comp);
		
		Produit prd = prod.getProduit(comp.getProduit().getIdProduit());
		
		if(( prd.getQuantite() - comp.getProduit().getQuantite())>0 ) {
			
			composante.decrementer(comp.getProduit());
			
		}else {
			return;
		}
		
	}

	@Override
	public void ModifierComposante(Composante comp, Long id) {

		Composante oldone=composante.LaComposante(id);
		int diff =  comp.getQuantite() - oldone.getQuantite();
		composante.ModifierComposante(comp, id);
		
	}

	@Override
	public Composante LaComposante(Long comp) {
		
		return composante.LaComposante(comp);
		
	}

	@Override
	public void incrementer(Produit p) {

		composante.incrementer(p);
	}

	@Override
	public void decrementer(Produit p) {
		
		composante.decrementer(p);
		
	}

}
