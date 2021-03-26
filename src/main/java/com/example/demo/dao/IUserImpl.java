package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.User;

@Component
public class IUserImpl implements IUserDAO{
	
	@Autowired
	private EntityManager entity;
	
	@Override
	@Transactional
	public void ajouterUser(User u) {
	
			entity.persist(u);
		
	}

	@Override
	@Transactional
	public List<User> listeclients() {

		Query ql =entity.createQuery("FROM User");
		List<User> lsc =ql.getResultList();
		
		return lsc;
		
	}

	@Override
	@Transactional
	public User FindUser(int id) {

		User u=entity.find(User.class, id);
		
		return u;
		
	}

	@Override
	@Transactional
	public void SupprimerUser(int u) {

		User util =entity.find(User.class, u);
		entity.remove(util);
		
	}

	@Override
	@Transactional
	public void EditUser(User u ,int id) {

		User t = entity.find(User.class, id);
		t.setAdresse(u.getAdresse());
		t.setCin(u.getCin());
		t.setConnection(u.isConnection());
		t.setEmail(u.getEmail());
		t.setNom(u.getNom());
		t.setNtelephone(u.getNtelephone());
		t.setPassword(u.getPassword());
		t.setPrenom(u.getPrenom());
		t.setRole(u.getRole());
		t.setPanier(u.getPanier());
		t.setUsername(u.getUsername());
		entity.persist(t);
		
	}
	
	
}
