package com.example.demo.contollers;

import java.util.List;
 

public interface InterfaceDao<T> {
	
	public void ajouter(T u);
	
	public List<T> liste();
	
	public T chercher(Long u);
	
	public void supprimer(Long u); 
		
	public void editer(T u,Long e);

}
