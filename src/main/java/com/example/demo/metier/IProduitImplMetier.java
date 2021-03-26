package com.example.demo.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IProduitDAO;
import com.example.demo.entities.Produit;

@Service
public class IProduitImplMetier implements IProduitMetier{

	@Autowired
	IProduitDAO prod;
	
	public void setProd(IProduitDAO prod) {
		
		this.prod = prod;
		
	}
	
	@Override
	public void ajouterProduit(Produit p, int idCat) {
		
		prod.ajouterProduit(p, idCat);
		
	}

	@Override
	public List<Produit> listeproduits() {
		
		return prod.listeproduits();
		
	}

	@Override
	public List<Produit> produitsParMotCle(String mc) {
		
		return prod.produitsParMotCle(mc);
		
	}

	@Override
	public List<Produit> produitsParCategorie(int idCat) {
		
		return prod.produitsParCategorie(idCat);
		
	}

	@Override
	public Produit getProduit(int idP) {
		
		return prod.getProduit(idP);
		
	}

	@Override
	public void supprimerProduit(int idP) {

		prod.supprimerProduit(idP);
		
	}

	@Override
	public void modifierProduit(Produit p, int id) {


		prod.modifierProduit(p, id);
		
	}

}
