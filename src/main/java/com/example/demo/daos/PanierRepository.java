package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Integer> {

}
