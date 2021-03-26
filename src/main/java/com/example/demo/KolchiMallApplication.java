package com.example.demo;


import javax.annotation.PostConstruct;

import com.example.demo.daos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.ComposantsPanier;
import com.example.demo.metier.ICategorieImplMetier;
import com.example.demo.metier.ICommandeImplMetier;
import com.example.demo.metier.IComposanteImplMetier;
import com.example.demo.metier.IComposantsPanierImplMetier;
import com.example.demo.metier.IPanierImplMetier;
import com.example.demo.metier.IProduitImplMetier;
import com.example.demo.metier.IUserImplMetier;
import com.example.demo.metier.MD5Hash;

@SpringBootApplication
public class KolchiMallApplication {

	@Autowired
	UserRepository userRepository;
	
	//@Autowired
	//IUserImpl2 ty;
	
	//@Autowired
	//PanierRepository panRep;
	
	@Autowired
	ICategorieImplMetier cat;
	
	@Autowired
	ICommandeImplMetier commande;
	
	@Autowired
	IComposanteImplMetier composante;
	
	@Autowired
	IComposantsPanierImplMetier compoPanier;
	
	@Autowired
	IPanierImplMetier panier;
	
	@Autowired
	IProduitImplMetier produit;
	
	@Autowired
	IUserImplMetier user;
	
	

	@Autowired
	IUserImplMetier l;
	
	@Autowired
	MD5Hash hash;

	public static void main(String[] args) {
		SpringApplication.run(KolchiMallApplication.class, args);
	}

	
	@PostConstruct
	public void onceFinishedLoading() {
		// userImpl.ajouterUser(new User(true,"LARHCsHIM", "ISMsffAIL",
		// "AD28743s3","0670138002", "ismaillarhchim864@gmail.com",
		// "SMA3OLATE","123456789", "HAY MASSIRA 01 NR 416 TEMARA RABAT","ADMIN"));
 
		
		//MD5Encoder.encode("dsqdqsd".getBytes());
		 
		
		/*userRepository
				.save(new User(true, "LARHCsHIM", "ISMsffAIL", "AD28743s3", "0670138002", "ismaillarhchim864@gmail.com",
						"SMA3OLATE",String.valueOf( MD5Encoder.encode("123456789")), "HAY MASSIRA 01 NR 416 TEMARA RABAT", "ADMIN"));*/
         
	/*	ty.ajouter(new User(true,"LARHCsHIM", "ISMsffAIL",
				"AD28743s3","0670138002", "ismaillarhchim864@gmail.com",
				 "SMA3OLATE","123456789", "HAY MASSIRA 01 NR 416 TEMARA RABAT","ADMIN"));
		*/
	/*	Categorie c = new Categorie();
		c.setActived(true);
		c.setDescription("TELEPHONE");
		c.setNomCategorie("SAMSUNG");
		c.setPhoto("/D:/test"); 
	cat.ajouterCategorie(c); */
		/*List<Categorie> ls =cat.listeCategories();
		for (Categorie categorie : ls) {
			System.out.println(categorie.toString());
		}*/
		
		/*c=cat.getCategorie(Long.valueOf(5));
		System.out.println(c.toString());*/
		
		//cat.supprimerCategrorie(Long.valueOf(1));
		
		//cat.modifierCategorie(Long.valueOf(5), new Categorie("ismail","ismail","ismail",false));
		
		/*user.ajouterUser(new User(true,"BAAZI", "RANYA",
				"AD2874333","0670138002", "ismaillarhchim864@gmail.com",
				 "SMA3OLATE",hash.getMd5("123456789"), "HAY MASSIRA 01 NR 416 TEMARA RABAT","ADMIN"));*/
		//im controling youre laptop bro
		/*List<User> lsuser =user.listeclients();
		for (User o : lsuser) {
			System.out.println(o.toString());
		}*/
		
	    System.out.println("****************************************************************");
		System.out.println("****************************************************************");

		//System.out.println(userRepository.findByusername("SMA3OLATE"));
		/*User t =user.FindUser(1);
		System.out.println(t);*/
		
		
		//user.SupprimerUser(1);
		
		/*User r = new User();
		
		
		r.setAdresse("test");
		r.setCin("test");
		r.setConnection(false);
		r.setEmail("test");
		r.setNom("test");
		r.setNtelephone("test");
		r.setPassword("test");
		r.setPrenom("test");
		r.setRole("test");
		r.setUsername("test");
		
		user.EditUser(r,1);*/
		/*Produit r = new Produit();
		r.setDescription("test3");
		r.setDesignation("test3");
		r.setPhoto("test3");
		r.setPrix(30.0);
		r.setQuantite(120);
		r.setUrlphoto("test3");  
		produit.ajouterProduit(r, 1);*/
		/*List<Produit> lsProd = produit.listeproduits();
		for (Produit po : lsProd) {
			System.out.println(po.toString());
		}*/
	/*	
		List<Produit> lsp =produit.produitsParMotCle("T3");
		for (Produit pl : lsp) {
			System.out.println(pl.toString());   
		}
		*/
		/*List<Produit> lsp =produit.produitsParCategorie(2);
		int i=0;
		for (Produit kl : lsp) {
			System.out.println("this is number:===>"+i);
			System.out.println(kl.toString());
			i++;
		}*/
		
		//System.out.println(produit.getProduit(1).toString());
		
		//produit.supprimerProduit(1);
		
		/*Produit p = new Produit();
		p.setQuantite(100);
		p.setCategorie(cat.getCategorie(1));
		p.setDescription("test");
		p.setPhoto("test");
		p.setUrlphoto("test");
		p.setDesignation("test");
		p.setPrix(10.00);
		produit.modifierProduit(p, 1);
		panier.Ajouterpanier(new Panier(new Date())); */
		 
		//System.out.println(panier.FindPanier(3).toString()); 
		
		//panier.SupprimerPanier(3); 
		
	/*	ComposantsPanier cP = new ComposantsPanier();
		cP.setQuantite(11);
		cP.setProduit(produit.getProduit(1));
		compoPanier.AjouterComposantPanier(cP, panier.FindPanier(1));
		
		 
		/*ComposantsPanier cP = new ComposantsPanier();
		cP.setQuantite(110);
		cP.setProduit(produit.getProduit(3));
		cP.setPanier(panier.FindPanier(1));
		compoPanier.ModifierComposantPanier(cP, 1);*/
		
	//	ComposantsPanier xP =compoPanier.findComposant(4);
		//System.out.println(xP.toString());  
		//compoPanier.SupprimerComposantPanier(xP);
		
		//commande.ValiderCommande(1, user.FindUser(1), true, "temara");
	
		
		//todolist 
		/*
		 wmajrebtch hadok les autres methodes dyal commande 
		 */
		
		//compoPanier.SupprimerComposantPanier(2);
		
		//System.out.println(commande.ChargerCommande(2));
		
		//commande.AnnulerCommande(1);
		
		
		//commande.ModifierCommande(1, "KENITRA");

		//System.out.println(panRep.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0).getId());
		
		/*Panier r=panier.LastPanier(); 
		System.out.println(r.getId());
		System.out.println(r.toString());*/
		
		/*l.ajouterUser(new User(true,"BAAZI", "RANYA",
				"AD2874333","0670138002", "ismaillarhchim864@gmail.com",
				 "SMA3OLATE",hash.getMd5("123456789"), "HAY MASSIRA 01 NR 416 TEMARA RABAT","ADMIN"));*/
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");

	}
	
	
   
}
