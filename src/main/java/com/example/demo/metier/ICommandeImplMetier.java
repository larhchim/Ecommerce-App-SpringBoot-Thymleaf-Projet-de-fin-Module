package com.example.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ICommandeDAO;
import com.example.demo.entities.Commande;
import com.example.demo.entities.Composante;
import com.example.demo.entities.User;

@Service
public class ICommandeImplMetier implements ICommandeMetier{

	@Autowired
	ICommandeDAO com;
	
	public void setCom(ICommandeDAO com) {
		this.com = com;
	}
	
	@Override
	public Commande ChargerCommande(int id) {
		return com.ChargerCommande(id);
	}

	@Override
	public void AnnulerCommande(int c) {
		//todolist
		com.AnnulerCommande(c);
		
	}

	@Override
	public void AddComposantetoCommande(Composante c, Commande cmd) {
		
		//todolist
		com.AddComposantetoCommande(c, cmd);
		
	}

	@Override
	public int ValiderCommande(int idPanier, User c, boolean e, String local) {

		return com.ValiderCommande(idPanier, c, e, local);
	}

	@Override
	public void ModifierCommande(int id, String local) {
		
		com.ModifierCommande(id, local);
		
	}

}
