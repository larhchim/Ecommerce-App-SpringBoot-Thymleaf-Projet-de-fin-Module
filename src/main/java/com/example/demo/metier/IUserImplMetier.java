package com.example.demo.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDAO;
import com.example.demo.entities.User;

@Service
public class IUserImplMetier implements IUserMetier{
	
	@Autowired
	IUserDAO user;
	
	public void setUser(IUserDAO user) {
		this.user = user;
	}

	@Override
	public void ajouterUser(User u) {

		user.ajouterUser(u);
	}

	@Override
	public List<User> listeclients() {
		return user.listeclients();
	}

	@Override
	public User FindUser(int u) {
		return user.FindUser(u);
	}

	@Override
	public void SupprimerUser(int u) {

		user.SupprimerUser(u);
		
	}

	@Override
	public void EditUser(User u, int id) {

		user.EditUser(u, id);
	}

}
