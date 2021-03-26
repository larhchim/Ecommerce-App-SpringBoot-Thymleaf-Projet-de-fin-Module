package com.example.demo.dao;

import java.util.List;

import com.example.demo.daos.UserRepository;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface IUserDAO{
	
	public void ajouterUser(User u);
	
	public List<User> listeclients();
	
	public User FindUser(int u);
	
	public void SupprimerUser(int  u);
	
	public void EditUser(User u,int id);


}
