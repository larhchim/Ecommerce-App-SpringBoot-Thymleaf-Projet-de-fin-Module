package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CLIENT")
	private int idClient;
	
	private String nom;
	private String prenom;
	private String Cin;
	
	@Column(name="NumeroTelephone")
	private String Ntelephone;
	
	private String email;
	private String username;
	private String password;
	private String adresse;
	private boolean connection;
	private String role;
	
	@OneToOne
    @JoinColumn(name = "Id_Panier")
	private Panier panier;
	
	@OneToMany(mappedBy="user")
	private List<Commande> commandes ;
	
	
    public User(boolean conn,String nom, String prenom, String cin, String ntelephone, String email, String username,String password, String adresse, String role) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.Cin = cin;
		this.Ntelephone = ntelephone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.adresse = adresse;
		this.role = role;
		this.connection=conn;
		
	}
	
	
	
	public User() {
		
	}



	public List<Commande> getCommandes() {
		return commandes;
	}



	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCin() {
		return Cin;
	}
	public void setCin(String cin) {
		Cin = cin;
	}
	public String getNtelephone() {
		return Ntelephone;
	}
	public void setNtelephone(String ntelephone) {
		Ntelephone = ntelephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	


	public boolean isConnection() {
		return connection;
	}



	public void setConnection(boolean connection) {
		this.connection = connection;
	}



	@Override
	public String toString() {
		return "User [nom=" + nom + ", prenom=" + prenom + ", Cin=" + Cin + ", Ntelephone=" + Ntelephone + ", email="
				+ email + ", username=" + username + ", password=" + password + ", adresse=" + adresse + ", connection="
				+ connection + ", role=" + role + "]";
	}

	


	


	

}
