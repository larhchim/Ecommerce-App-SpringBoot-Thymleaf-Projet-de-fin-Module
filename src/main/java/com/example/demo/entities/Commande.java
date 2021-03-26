package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commande implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_COMMANDE")
	private int idCommande;
	
	private String localisation;
	private Date dateCommande;
	private boolean etat;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private User user;
	
	@OneToMany(mappedBy="commande", cascade = CascadeType.ALL)
	private List<Composante> composante;
	
	
	public Commande(String localisation, Date dateCommande, boolean etat) {
		
		this.localisation = localisation;
		this.dateCommande = dateCommande;
		this.etat = etat;
		
	}
	
	
	public Commande() {
		
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}


	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Composante> getComposante() {
		return composante;
	}


	public void setComposante(List<Composante> composante) {
		this.composante = composante;
	}


	@Override
	public String toString() {
		return "Commande [localisation=" + localisation + ", dateCommande=" + dateCommande + ", etat=" + etat + "]"+"\n";
	}
	
	
	

}
