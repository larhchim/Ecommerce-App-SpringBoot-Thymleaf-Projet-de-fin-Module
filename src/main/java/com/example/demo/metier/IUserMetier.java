package com.example.demo.metier;

import java.util.List;

import com.example.demo.entities.User;

public interface IUserMetier {
	
public void ajouterUser(User u);
	
	public List<User> listeclients();
	
	public User FindUser(int u);
	
	public void SupprimerUser(int  u);
	
	public void EditUser(User u,int id);


}
